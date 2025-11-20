package com.example.shop.order.dto;

import com.example.shop.common.message.ErrorMessage;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderCreateRequest {

    @NotNull(message = ErrorMessage.TIME_NOT_NULL)
    private LocalDateTime time;

    public OrderCreateRequest(LocalDateTime time) {
        this.time = time;
    }
}
