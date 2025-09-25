package com.nianci.springmall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nianci.springmall.repository.RoleRepository;
import com.nianci.springmall.repository.UserRepository;
import com.nianci.springmall.dto.LoginRequest;
import com.nianci.springmall.dto.RegisterRequest;
import com.nianci.springmall.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void register_success() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("test1");
        registerRequest.setPassword("123");

        String json = objectMapper.writeValueAsString(registerRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", equalTo("User registered!")));

        Optional<User> userOpt = userRepository.findByUsername(registerRequest.getUsername());
        assertTrue(userOpt.isPresent());
        User user = userOpt.get();
        assertNotEquals(registerRequest.getPassword(), user.getPassword());

        assertTrue(user.getRoles().stream()
                .anyMatch(r -> r.getName().equals("ROLE_CUSTOMER")));
    }

    @Test
    public void register_usernameAlreadyExist() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("test2");
        registerRequest.setPassword("123");

        String json = objectMapper.writeValueAsString(registerRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Test
    public void login_success() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("test3");
        registerRequest.setPassword("123");

        register(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(registerRequest.getUsername());
        loginRequest.setPassword(registerRequest.getPassword());

        String json = objectMapper.writeValueAsString(loginRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.token", notNullValue()));
    }

    @Test
    public void login_wrongPassword() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("test4");
        registerRequest.setPassword("123");

        register(registerRequest);

        LoginRequest userLoginRequest = new LoginRequest();
        userLoginRequest.setUsername(registerRequest.getUsername());
        userLoginRequest.setPassword("unknown");

        String json = objectMapper.writeValueAsString(userLoginRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Test
    public void login_usernameNotExist() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("unknown");
        loginRequest.setPassword("123");

        String json = objectMapper.writeValueAsString(loginRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    private void register(RegisterRequest registerRequest) throws Exception {
        String json = objectMapper.writeValueAsString(registerRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }
}
