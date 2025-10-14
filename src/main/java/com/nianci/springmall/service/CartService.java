package com.nianci.springmall.service;

import com.nianci.springmall.dto.CartItemResponse;

import java.util.List;

public interface CartService {
    List<CartItemResponse> getCartByUsername(String username);
    CartItemResponse addToCart(String username, Long productId, int quantity);
    CartItemResponse updateCartItem(Long cartItemId, int quantity);
    void removeCartItem(Long cartItemId);
    boolean isNewCartItem(String username, Long productId);
}
