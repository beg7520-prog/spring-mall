# Spring Mall — Spring Boot E-Commerce Demo

This is a **Spring Mall Demo project** built with **Spring Boot, Spring Security, JPA/MySQL**, and **JWT**.  
It simulates a basic e-commerce workflow — from **user registration** to **order management**.

---

## Project Overview

### Purpose
Simulate a **small online shop workflow**, including:
1. User registration & login (JWT authentication)
2. Product listing, details, and search/filter
3. Shopping cart CRUD
4. Placing orders & viewing order history
5. Admin dashboard for viewing all orders

---

## Tech Stack

| Layer | Technology |
|-------|-------------|
| **Framework** | Spring Boot 3 |
| **Security** | Spring Security + JWT |
| **Database** | MySQL / JPA / Hibernate |
| **Build Tool** | Maven |
| **Testing** | JUnit 5, MockMvc |
| **Deployment (optional)** | Render / Railway / Docker |

---

## Project Structure (Simplified)

```bash
springmall/
├─ entity/        # Entity definitions (JPA models)
│   ├─ User.java
│   ├─ Role.java
│   ├─ Product.java
│   ├─ CartItem.java
│   └─ Order.java
│
├─ repository/    # JPA Repositories (CRUD operations)
│   ├─ UserRepository.java
│   ├─ ProductRepository.java
│   ├─ CartRepository.java
│   └─ OrderRepository.java
│
├─ service/       # Business logic layer
│   ├─ ProductService.java / ProductServiceImpl.java
│   ├─ CartService.java / CartServiceImpl.java
│   ├─ OrderService.java / OrderServiceImpl.java
│   └─ AuthService.java / AuthServiceImpl.java
│
├─ controller/    # REST API controllers
│   ├─ AuthController.java
│   ├─ ProductController.java
│   ├─ CartController.java
│   ├─ OrderController.java
│   └─ AdminController.java
├─exception/
│   └─ GlobalExceptionHandler.java
│
├─ util/          # Utility classes
│   └─ JwtUtil.java
│
├─ filter/        # Request filters
│   └─ JwtFilter.java
│
├─ config/        # Security & application configuration
│   └─ SecurityConfig.java
│
└─ dto/           # Request/Response data objects
    ├─ RegisterRequest.java
    ├─ LoginRequest.java
    ├─ JwtResponse.java
    └─ OrderResponse.java
```

> Controllers → Services → Repositories → Entities  
> Clear separation of responsibilities following the MVC architecture.

---

## Core APIs

| **Module** | **Endpoint** | **Action** |
|-------------|---------------|------------|
| **Auth** | `POST /api/auth/register` | Register new user |
| **Auth** | `POST /api/auth/login` | Login → obtain JWT |
| **Product** | `GET /api/products` | List all products (supports keyword/category filters) |
| **Product** | `GET /api/products/{id}` | Get product details |
| **Cart** | `GET /api/cart` | View shopping cart |
| **Cart** | `POST /api/cart` | Add item to cart |
| **Cart** | `PUT /api/cart/{itemId}` | Update quantity |
| **Cart** | `DELETE /api/cart/{itemId}` | Remove item |
| **Order** | `POST /api/orders` | Place order (clears cart) |
| **Order** | `GET /api/orders` | View user’s order history |
| **Admin** | `GET /api/admin/orders` | View all orders (admin only) |

> Workflow: Register → Login → Browse Products → Add to Cart → Place Order → View Orders  
> Admin can view and manage all customer orders.

---
