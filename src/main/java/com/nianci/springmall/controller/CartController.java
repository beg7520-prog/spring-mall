package com.nianci.springmall.controller;

import com.nianci.springmall.dto.CartItemRequest;
import com.nianci.springmall.dto.CartItemResponse;
import com.nianci.springmall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartItemResponse>> getCart(@AuthenticationPrincipal String username) {
        List<CartItemResponse> cartItems = cartService.getCartByUsername(username);
        return ResponseEntity.ok(cartItems);
    }

    @PostMapping
    public ResponseEntity<CartItemResponse> addToCart(@AuthenticationPrincipal String username,
                                                      @RequestBody CartItemRequest request) {
        CartItemResponse item = cartService.addToCart(username, request.getProductId(), request.getQuantity());
        return ResponseEntity.ok(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItemResponse> updateCart(@PathVariable Long id,
                                                       @RequestBody CartItemRequest request) {
        CartItemResponse updated = cartService.updateCartItem(id, request.getQuantity());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long id) {
        cartService.removeCartItem(id);
        return ResponseEntity.noContent().build();
    }
}
