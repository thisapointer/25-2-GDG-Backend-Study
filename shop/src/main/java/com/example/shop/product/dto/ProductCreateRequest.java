package com.example.shop.product.dto;

import lombok.Getter;

@Getter
public class ProductCreateRequest {

    private String name;
    private int price;

    public ProductCreateRequest(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
