package com.example.shop.product.service;

import com.example.shop.product.dto.ProductCreateRequest;
import com.example.shop.product.dto.ProductUpdateRequest;
import com.example.shop.product.entity.Product;

import java.util.List;

public interface ProductService {
    Long createProduct(ProductCreateRequest request);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void updateProduct(Long id, ProductUpdateRequest request);
    void deleteProduct(Long id);
}
