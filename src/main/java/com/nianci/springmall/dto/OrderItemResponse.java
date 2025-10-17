package com.nianci.springmall.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderItemResponse {
    private String productName;
    private Integer quantity;
    private Double price;
    private Double subtotal;
}
