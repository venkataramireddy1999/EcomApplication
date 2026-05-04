package com.nonsyncbobbal.EcomApplication.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {
    private Integer Id;
    private Integer productId;
    private Integer quantity;
    private BigDecimal price;
}
