package com.nianci.springmall.service.impl;

import com.nianci.springmall.dto.LoginRequest;
import com.nianci.springmall.dto.RegisterRequest;
import com.nianci.springmall.entity.Role;
import com.nianci.springmall.entity.User;
import com.nianci.springmall.repository.RoleRepository;
import com.nianci.springmall.repository.UserRepository;
import com.nianci.springmall.service.AuthService;
import com.nianci.springmall.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    @Override
    public void register(RegisterRequest req) {
        if (userRepo.existsByUsername(req.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(encoder.encode(req.getPassword()));

        Role customerRole = roleRepo.findByName("ROLE_CUSTOMER")
                .orElseThrow(() -> new RuntimeException("ROLE_CUSTOMER not found"));
        user.getRoles().add(customerRole);

        userRepo.save(user);
    }

    @Override
    public String login(LoginRequest req) {
        User user = userRepo.findByUsername(req.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));

        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
        }

        return jwtUtil.generateToken(user);
    }
}
