package com.nianci.springmall.service;

import com.nianci.springmall.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {
    Page<Product> searchProducts(String keyword, String category, Pageable pageable);
    Optional<Product> getProductById(Long id);
}
