package com.example.shop.order.service;

import com.example.shop.order.dto.OrderCreateRequest;
import com.example.shop.order.entity.Order;

import java.util.List;

public interface OrderService {
    Long createOrder(OrderCreateRequest orderCreateRequest);
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    void deleteOrder(Long id);
}
