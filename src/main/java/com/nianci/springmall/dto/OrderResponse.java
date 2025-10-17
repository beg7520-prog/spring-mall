package com.nianci.springmall.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderResponse {
    private Long orderId;
    private Double total;
    private String username;
    private List<OrderItemResponse> items;
}
