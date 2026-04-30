package com.nonsyncbobbal.EcomApplication.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {
    private Integer id;
    private String name;
    private String description;
    private String category;
    private String imageURL;
    private BigDecimal price;
    private int stockQuantity;
    private Boolean isActive;
}
