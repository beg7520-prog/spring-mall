package com.nianci.springmall.service;

import com.nianci.springmall.dto.OrderResponse;
import java.util.List;

public interface AdminService {
    List<OrderResponse> getAllOrders();
}
