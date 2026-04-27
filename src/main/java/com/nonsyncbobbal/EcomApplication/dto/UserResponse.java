package com.nonsyncbobbal.EcomApplication.dto;

import com.nonsyncbobbal.EcomApplication.model.UserRole;
import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole userRole;
    private AddressDTO address;
}
