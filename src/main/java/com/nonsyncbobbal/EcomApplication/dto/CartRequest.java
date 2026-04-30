package com.nonsyncbobbal.EcomApplication.dto;

import lombok.Data;

@Data
public class CartRequest {
    private int productId;
    private int quantity;

}
