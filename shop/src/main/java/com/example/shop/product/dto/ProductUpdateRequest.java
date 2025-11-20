package com.example.shop.product.dto;

import com.example.shop.common.message.ErrorMessage;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ProductUpdateRequest {

    @Size(min = 1, max = 50, message = ErrorMessage.NAME_SIZE)
    private String name;
    private Integer price;

    public ProductUpdateRequest(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
