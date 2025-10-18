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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getAllOrders_notLoggedIn_unauthorized() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/admin/orders");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(401));
    }

    @Test
    public void getAllOrders_asUser_forbidden() throws Exception {
        String token = getJwtToken(mockMvc, objectMapper, "user1", "654321");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/admin/orders")
                .header("Authorization", "Bearer " + token);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(403));
    }

    @Test
    public void getAllOrders_asAdmin_returnsOrderList() throws Exception {
        String token = getJwtToken(mockMvc, objectMapper, "admin1", "123456");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/admin/orders")
                .header("Authorization", "Bearer " + token);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
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
