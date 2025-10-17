package com.nianci.springmall.service;

import com.nianci.springmall.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse placeOrder(String username);
    List<OrderResponse> getOrders(String username);
}
