package com.nianci.springmall.service.impl;

import com.nianci.springmall.dto.CartItemRequest;
import com.nianci.springmall.dto.OrderItemResponse;
import com.nianci.springmall.dto.OrderResponse;
import com.nianci.springmall.entity.*;
import com.nianci.springmall.exception.BadRequestException;
import com.nianci.springmall.exception.ResourceNotFoundException;
import com.nianci.springmall.repository.*;
import com.nianci.springmall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;
    private final CartRepository cartRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    @Override
    @Transactional
    public OrderResponse placeOrder(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<CartItem> cartItems = cartRepo.findByUser(user);
        if (cartItems.isEmpty()) {
            throw new BadRequestException("Cart is empty");
        }

        double total = cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        Order order = new Order();
        order.setUser(user);
        order.setTotal(total);
        order.setStatus("PENDING");

        List<OrderItem> orderItems = cartItems.stream()
                .map(item -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setProduct(item.getProduct());
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setPrice(item.getProduct().getPrice());
                    return orderItem;
                })
                .toList();

        order.setItems(orderItems);
        orderRepo.save(order);

        cartRepo.deleteAll(cartItems);

        return toDto(order);
    }

    @Override
    public List<OrderResponse> getOrders(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Order> orders = orderRepo.findByUser(user);

        return orders.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderResponse placeOrderWithCart(String username, List<CartItemRequest> cartItems) {
        if (cartItems.isEmpty()) {
            throw new BadRequestException("Cart is empty");
        }

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        double total = cartItems.stream()
                .mapToDouble(item -> {
                    Product product = productRepo.findById(item.getProductId())
                            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
                    return product.getPrice() * item.getQuantity();
                })
                .sum();

        Order order = new Order();
        order.setUser(user);
        order.setTotal(total);
        order.setStatus("PENDING");

        List<OrderItem> orderItems = cartItems.stream()
                .map(item -> {
                    Product product = productRepo.findById(item.getProductId())
                            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setProduct(product);
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setPrice(product.getPrice());
                    return orderItem;
                })
                .toList();

        order.setItems(orderItems);
        orderRepo.save(order);

        return toDto(order);
    }

    private OrderResponse toDto(Order order) {
        List<OrderItemResponse> items = order.getItems() != null
                ? order.getItems().stream()
                .map(item -> new OrderItemResponse(
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPrice(),
                        item.getPrice() * item.getQuantity()
                ))
                .toList()
                : List.of();

        return new OrderResponse(
                order.getId(),
                order.getTotal(),
                order.getUser().getUsername(),
                items
        );
    }
}
