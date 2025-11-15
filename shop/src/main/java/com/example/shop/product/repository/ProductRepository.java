package com.example.shop.product.repository;

import com.example.shop.product.entity.Product;

import java.util.List;

public interface ProductRepository {
    Product findById(Long id);
    List<Product> findAll();
    Product findByName(String name);
    void save(Product product);
    void deleteById(Long id);
}
