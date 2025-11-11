package com.example.shop.order.dto;

import com.example.shop.member.Member;
import com.example.shop.orderProduct.OrderProduct;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderCreateRequest {

    private LocalDateTime time;

    public OrderCreateRequest(LocalDateTime time) {
        this.time = time;
    }
}
