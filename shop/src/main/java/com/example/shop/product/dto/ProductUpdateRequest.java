package com.example.shop.product.dto;

import lombok.Getter;

@Getter
public class ProductUpdateRequest {

    private String name;
    private int price;

    public ProductUpdateRequest(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
