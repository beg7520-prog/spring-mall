package com.nianci.springmall.controller;

import com.nianci.springmall.dto.JwtResponse;
import com.nianci.springmall.dto.LoginRequest;
import com.nianci.springmall.dto.RegisterRequest;
import com.nianci.springmall.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest req) {
        authService.register(req);
        return ResponseEntity.ok("User registered!");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid LoginRequest req) {
        String token = authService.login(req);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
