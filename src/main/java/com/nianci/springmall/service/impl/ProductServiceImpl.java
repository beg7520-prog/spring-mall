package com.nianci.springmall.service.impl;

import com.nianci.springmall.entity.Product;
import com.nianci.springmall.repository.ProductRepository;
import com.nianci.springmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;

    @Override
    public Page<Product> searchProducts(String keyword, String category, Pageable pageable) {
        return productRepo.searchByKeywordAndCategory(keyword, category, pageable);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepo.findById(id);
    }
}
