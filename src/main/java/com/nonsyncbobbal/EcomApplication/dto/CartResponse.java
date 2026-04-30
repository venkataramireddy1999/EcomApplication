package com.nonsyncbobbal.EcomApplication.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartResponse {
    private Integer userId;
    private Integer productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;

}
