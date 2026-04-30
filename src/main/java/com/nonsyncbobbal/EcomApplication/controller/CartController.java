package com.nonsyncbobbal.EcomApplication.controller;

import com.nonsyncbobbal.EcomApplication.dto.CartRequest;
import com.nonsyncbobbal.EcomApplication.dto.CartResponse;
import com.nonsyncbobbal.EcomApplication.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart( @RequestHeader("User-ID") String userId,
            @RequestBody CartRequest cartRequest) {
        if(cartService.addToCart(userId, cartRequest))
            return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.badRequest().body("Product is out of stock or User not found or Product not found");
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> deleteFromCart(@RequestHeader("User-ID") String userId
                                                ,@PathVariable int productId) {
        return cartService.deleteFromCart(userId,productId) ? ResponseEntity.noContent().build()
                                                            : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<CartResponse>> getCart(@RequestHeader("User-ID") String userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }
}
