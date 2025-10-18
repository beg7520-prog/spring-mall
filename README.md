# 🛒 Spring Mall — Spring Boot E-Commerce Demo

This is a **Spring Mall Demo project** built with **Spring Boot, Spring Security, JPA/MySQL**, and **JWT**.  
It simulates a basic e-commerce workflow — from **user registration** to **order management**.

---

## 🚀 Project Overview

### 🎯 Purpose
Simulate a **small online shop workflow**, including:
1. User registration & login (JWT authentication)
2. Product listing, details, and search/filter
3. Shopping cart CRUD
4. Placing orders & viewing order history
5. Admin dashboard for viewing all orders

---

## ⚙️ Tech Stack

| Layer | Technology |
|-------|-------------|
| **Framework** | Spring Boot 3 |
| **Security** | Spring Security + JWT |
| **Database** | MySQL / JPA / Hibernate |
| **Build Tool** | Maven |
| **Testing** | JUnit 5, MockMvc |
| **Deployment (optional)** | Render / Railway / Docker |

---

## 🧩 Project Structure (Simplified)

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

> ✅ Controllers → Services → Repositories → Entities  
> Clear separation of responsibilities following the MVC architecture.

---

## 🔑 Core APIs

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

> 🧭 Workflow: Register → Login → Browse Products → Add to Cart → Place Order → View Orders  
> 👑 Admin can view and manage all customer orders.

---

## 🧰 Setup & Run

### 1️⃣ Clone Repository
```bash
git clone https://github.com/beg7520-prog/springmall.git
cd springmall
```

2️⃣ Configure Database (MySQL)

```bash
CREATE DATABASE spring-mall;

Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/spring-mall
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

3️⃣ Run Application

```bash
mvn spring-boot:run
```

4️⃣ Access Endpoints

```bash
Default base URL:

http://localhost:8080/api
```

⸻

🧪 Testing

```bash
The project includes MockMvc tests for major controllers:

Test Class	Purpose
AuthControllerTest	Register & login tests
CartControllerTest	Add/update/delete cart item
OrderControllerTest	Place order & fetch history
AdminControllerTest	Verify admin privileges

Run all tests:

mvn test
```

⸻

☁️ Deployment (Optional)

```bash
You can deploy to any free platform:
	•	🟢 Render — easiest way to host Spring Boot apps
	•	🟣 Railway — supports MySQL + Java
	•	🟠 Koyeb — lightweight app deployment
	•	🐳 Docker — containerized deployment with MySQL
```

⸻

📘 ER Diagram

```bash
User (id, username, password, role)
Product (id, name, price, description)
CartItem (id, user_id, product_id, quantity)
Order (id, user_id, total, created_at)
OrderItem (id, order_id, product_id, quantity, subtotal)
```

⸻

👤 Author

```bash
Created by Nianci
💻 Java Developer | Backend Enthusiast
📫 GitHub
```

⸻

🪪 License

```bash
This project is released under the MIT License.
```
