package com.nonsyncbobbal.EcomApplication.service;

import com.nonsyncbobbal.EcomApplication.dto.OrderResponse;
import com.nonsyncbobbal.EcomApplication.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderResponse placeOrder(String userId) {
        //Validate the cart items
            return new OrderResponse();
    }
}
