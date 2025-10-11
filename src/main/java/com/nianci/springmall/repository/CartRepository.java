package com.nianci.springmall.repository;

import com.nianci.springmall.entity.CartItem;
import com.nianci.springmall.entity.Product;
import com.nianci.springmall.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    Optional<CartItem> findByUserAndProduct(User user, Product product);
}
