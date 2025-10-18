package com.nianci.springmall.service.impl;

import com.nianci.springmall.dto.OrderItemResponse;
import com.nianci.springmall.dto.OrderResponse;
import com.nianci.springmall.entity.Order;
import com.nianci.springmall.repository.OrderRepository;
import com.nianci.springmall.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final OrderRepository orderRepository;

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
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
