package com.example.shop.order.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderCreateRequest {

    private LocalDateTime time;

    public OrderCreateRequest(LocalDateTime time) {
        this.time = time;
    }
}
