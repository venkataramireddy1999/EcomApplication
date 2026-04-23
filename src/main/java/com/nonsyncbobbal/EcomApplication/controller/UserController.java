package com.nonsyncbobbal.EcomApplication.controller;

import com.nonsyncbobbal.EcomApplication.model.User;
import com.nonsyncbobbal.EcomApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }
    @PostMapping("/api/users")
    public ResponseEntity<String> addUser(@RequestBody User user){
        return  ResponseEntity.ok(userService.addUser(user));

    }
    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        if (userService.getUserById(id).isPresent()) {
            return ResponseEntity.ok(userService.getUserById(id).get());
        }
        return ResponseEntity.notFound().build();
    }
}
