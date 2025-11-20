package com.example.shop.order.controller;

import com.example.shop.order.service.OrderService;
import com.example.shop.order.entity.Order;
import com.example.shop.order.dto.OrderCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Tag(name = "주문 관리", description = "주문 CRUD API")
public class OrderController {

    final private OrderService orderService;

    @PostMapping
    @Operation(summary = "주문 생성", description = "새로운 주문을 등록합니다.")
    @ApiResponse(responseCode = "400", description = "잘못된 요청 (유효성 검사 실패)")
    public ResponseEntity<Void> createOrder(@RequestBody @Valid OrderCreateRequest request) {
        Long id = orderService.createOrder(request);
        return ResponseEntity.created(URI.create("/orders/" + id)).build();
    }

    @GetMapping
    @Operation(summary = "모든 주문 조회", description = "모든 주문을 조회합니다.")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "주문 조회", description = "주문을 조회합니다.")
    @ApiResponse(responseCode = "400", description = "잘못된 요청 (유효성 검사 실패)")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{orderId}")
    @Operation(summary = "주문 삭제", description = "주문을 삭제합니다.")
    @ApiResponse(responseCode = "400", description = "잘못된 요청 (유효성 검사 실패)")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
