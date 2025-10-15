package com.nianci.springmall.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nianci.springmall.dto.CartItemRequest;
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

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getCart_unauthorized_returns401() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/cart");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(401));
    }

    @Test
    public void getCart_success() throws Exception {
        String token = getJwtToken(mockMvc, objectMapper, "user1", "654321");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/cart")
                .header("Authorization", "Bearer " + token);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void getCart_emptyCart_returnsEmptyList() throws Exception {
        String token = getJwtToken(mockMvc, objectMapper, "user2", "789012");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/cart")
                .header("Authorization", "Bearer " + token);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Transactional
    @Test
    public void addToCart_newItem_returns201() throws Exception {
        String token = getJwtToken(mockMvc, objectMapper, "user1", "654321");

        CartItemRequest cartItemRequest = new CartItemRequest();
        cartItemRequest.setProductId(4L);
        cartItemRequest.setQuantity(5);

        String json = objectMapper.writeValueAsString(cartItemRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/cart")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.productId", equalTo(4)))
                .andExpect(jsonPath("$.productName", equalTo("原木書架 Wooden Bookshelf")))
                .andExpect(jsonPath("$.price", equalTo(3200.0)))
                .andExpect(jsonPath("$.quantity", equalTo(5)));
    }

    @Transactional
    @Test
    public void addToCart_existingItem_increasesQuantity() throws Exception {
        String token = getJwtToken(mockMvc, objectMapper, "user1", "654321");

        CartItemRequest cartItemRequest = new CartItemRequest();
        cartItemRequest.setProductId(3L);
        cartItemRequest.setQuantity(1);

        String json = objectMapper.writeValueAsString(cartItemRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/cart")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", equalTo(3)))
                .andExpect(jsonPath("$.productName", equalTo("冷泡咖啡禮盒 Cold Brew Coffee Gift Set")))
                .andExpect(jsonPath("$.price", equalTo(850.0)))
                .andExpect(jsonPath("$.quantity", equalTo(2)));
    }


    @Transactional
    @Test
    public void addToCart_productNotFound_throwsException() throws Exception {
        String token = getJwtToken(mockMvc, objectMapper, "user1", "654321");

        CartItemRequest cartItemRequest = new CartItemRequest();
        cartItemRequest.setProductId(300L);
        cartItemRequest.setQuantity(1);

        String json = objectMapper.writeValueAsString(cartItemRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/cart")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404));
    }

    @Transactional
    @Test
    public void updateCartItem_success() throws Exception {
        String token = getJwtToken(mockMvc, objectMapper, "user1", "654321");

        CartItemRequest cartItemRequest = new CartItemRequest();
        cartItemRequest.setProductId(3L);
        cartItemRequest.setQuantity(2);

        String json = objectMapper.writeValueAsString(cartItemRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/cart/{cartItemId}", 2)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.productId", equalTo(3)))
                .andExpect(jsonPath("$.productName", equalTo("冷泡咖啡禮盒 Cold Brew Coffee Gift Set")))
                .andExpect(jsonPath("$.price", equalTo(850.0)))
                .andExpect(jsonPath("$.quantity", equalTo(2)));
    }

    @Transactional
    @Test
    public void updateCartItem_cartItemNotFound_throwsException() throws Exception {
        String token = getJwtToken(mockMvc, objectMapper, "user1", "654321");

        CartItemRequest cartItemRequest = new CartItemRequest();
        cartItemRequest.setProductId(3L);
        cartItemRequest.setQuantity(2);

        String json = objectMapper.writeValueAsString(cartItemRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/cart/{cartItemId}", 20000)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404));
    }

    @Transactional
    @Test
    public void removeCartItem_success() throws Exception {
        String token = getJwtToken(mockMvc, objectMapper, "user1", "654321");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/cart/{cartItemId}", 2)
                .header("Authorization", "Bearer " + token);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(204));
    }

    @Transactional
    @Test
    public void removeCartItem_itemNotFound() throws Exception {
        String token = getJwtToken(mockMvc, objectMapper, "user1", "654321");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/cart/{cartItemId}", 20000)
                .header("Authorization", "Bearer " + token);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(204));
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
