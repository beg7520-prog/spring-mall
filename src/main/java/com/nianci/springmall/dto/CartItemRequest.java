package com.nianci.springmall.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequest {
    @NonNull
    private Long productId;
    @NonNull
    private int quantity;
}
