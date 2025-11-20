package com.example.shop.product.dto;

import com.example.shop.common.message.ErrorMessage;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ProductCreateRequest {

    @NotNull(message = ErrorMessage.NAME_NOT_NULL)
    @Size(min = 1, max = 50, message = ErrorMessage.NAME_SIZE)
    private String name;

    @NotNull(message = ErrorMessage.PRICE_NOT_NULL)
    private int price;

    public ProductCreateRequest(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
