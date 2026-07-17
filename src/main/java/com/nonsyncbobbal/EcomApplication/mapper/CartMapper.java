package com.nonsyncbobbal.EcomApplication.mapper;

import com.nonsyncbobbal.EcomApplication.dto.CartResponse;
import com.nonsyncbobbal.EcomApplication.model.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartMapper {
    public static List<CartResponse> mapToCartResponse(List<Cart> carts) {
        List<CartResponse> cartResponseList = new ArrayList<>();
        for (Cart cart : carts) {
            CartResponse cartResponse = new CartResponse();
            cartResponse.setUserId(cart.getUser().getId());
            cartResponse.setProductId(cart.getProduct().getId());
            cartResponse.setProductName(cart.getProduct().getName());
            cartResponse.setQuantity(cart.getQuantity());
            cartResponse.setPrice(cart.getPrice());
            cartResponseList.add(cartResponse);

        }
        return cartResponseList;
    }
}
