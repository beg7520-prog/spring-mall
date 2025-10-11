package com.nianci.springmall.service.impl;

import com.nianci.springmall.dto.CartItemResponse;
import com.nianci.springmall.entity.CartItem;
import com.nianci.springmall.entity.Product;
import com.nianci.springmall.entity.User;
import com.nianci.springmall.repository.CartRepository;
import com.nianci.springmall.repository.ProductRepository;
import com.nianci.springmall.repository.UserRepository;
import com.nianci.springmall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    @Override
    public List<CartItemResponse> getCartByUsername(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartRepo.findByUser(user)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public CartItemResponse addToCart(String username, Long productId, int quantity) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem item = cartRepo.findByUserAndProduct(user, product)
                .orElse(new CartItem());

        if (item.getId() == null) {
            item.setUser(user);
            item.setProduct(product);
            item.setQuantity(quantity);
        } else {
            item.setQuantity(item.getQuantity() + quantity);
        }

        CartItem saved = cartRepo.save(item);
        return toDto(saved);
    }

    @Override
    public CartItemResponse updateCartItem(Long cartItemId, int quantity) {
        CartItem item = cartRepo.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        item.setQuantity(quantity);
        return toDto(cartRepo.save(item));
    }

    @Override
    public void removeCartItem(Long cartItemId) {
        cartRepo.deleteById(cartItemId);
    }

    private CartItemResponse toDto(CartItem item) {
        return new CartItemResponse(
                item.getId(),
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getProduct().getPrice(),
                item.getQuantity(),
                item.getProduct().getPrice() * item.getQuantity()
        );
    }
}
