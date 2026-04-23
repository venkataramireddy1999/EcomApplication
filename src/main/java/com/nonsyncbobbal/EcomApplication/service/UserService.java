package com.nonsyncbobbal.EcomApplication.service;

import com.nonsyncbobbal.EcomApplication.model.User;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Getter
    List<User> users = new ArrayList<>(List.of(new User(1, "Venkata", "Ramireddy"), new User(2, "Nikolay", "Nikolay")));
    private int id = 3;

    public String addUser(User user){
        user.setId(id++);
        users.add(user);
        return "User added successfully";
    }
}
