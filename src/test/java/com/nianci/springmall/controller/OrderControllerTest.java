package com.nianci.springmall.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nianci.springmall.dto.LoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getOrders_unauthorized_returns401() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/orders");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(401));
    }

    @Test
    public void getOrders_success() throws Exception {
        String token = getJwtToken(mockMvc, objectMapper, "user1", "654321");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/orders")
                .header("Authorization", "Bearer " + token);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].items", hasSize(2)));
    }

    @Test
    public void getOrders_emptyList() throws Exception {
        String token = getJwtToken(mockMvc, objectMapper, "user2", "789012");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/orders")
                .header("Authorization", "Bearer " + token);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Transactional
    @Test
    public void placeOrder_success() throws Exception {
        String token = getJwtToken(mockMvc, objectMapper, "user1", "654321");

        RequestBuilder requestBuilder_placeOrder = MockMvcRequestBuilders
                .post("/api/orders")
                .header("Authorization", "Bearer " + token);

        mockMvc.perform(requestBuilder_placeOrder)
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.orderId", equalTo(2)))
                .andExpect(jsonPath("$.total", equalTo(7330.0)))
                .andExpect(jsonPath("$.username", equalTo("user1")))
                .andExpect(jsonPath("$.items", hasSize(3)));

        RequestBuilder requestBuilder_cartEmpty = MockMvcRequestBuilders
                .get("/api/cart")
                .header("Authorization", "Bearer " + token);

        mockMvc.perform(requestBuilder_cartEmpty)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Transactional
    @Test
    public void placeOrder_cartEmpty() throws Exception {
        String token = getJwtToken(mockMvc, objectMapper, "user2", "789012");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/orders")
                .header("Authorization", "Bearer " + token);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    public String getJwtToken(MockMvc mockMvc, ObjectMapper objectMapper, String username, String password) throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        String json = objectMapper.writeValueAsString(loginRequest);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        JsonNode node = objectMapper.readTree(responseJson);
        return node.get("token").asText();
    }
}
