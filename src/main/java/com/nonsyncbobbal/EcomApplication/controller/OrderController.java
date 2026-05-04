package com.nonsyncbobbal.EcomApplication.controller;

import com.nonsyncbobbal.EcomApplication.dto.OrderResponse;
import com.nonsyncbobbal.EcomApplication.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@RequestHeader("User-ID") String userId) {
        OrderResponse orderResponse = orderService.placeOrder(userId);
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }
}
