package com.nonsyncbobbal.EcomApplication.dto;

import lombok.Data;

@Data
public class ProductResponse {
    private Integer id;
    private String name;
    private String description;
    private String category;
    private String imageURL;
    private double price;
    private int stockQuantity;
    private Boolean isActive;
}
