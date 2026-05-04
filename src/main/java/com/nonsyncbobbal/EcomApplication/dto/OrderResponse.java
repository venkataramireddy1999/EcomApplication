package com.nonsyncbobbal.EcomApplication.dto;

import com.nonsyncbobbal.EcomApplication.model.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private Integer Id;
    private BigDecimal price;
    private OrderStatus orderStatus;
    private List<OrderItemDTO> orderItemDTOList;
    private LocalDateTime createdAt;

}
