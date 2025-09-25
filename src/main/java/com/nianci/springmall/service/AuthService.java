package com.nianci.springmall.service;

import com.nianci.springmall.dto.LoginRequest;
import com.nianci.springmall.dto.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest req);
    String login(LoginRequest req);
}
