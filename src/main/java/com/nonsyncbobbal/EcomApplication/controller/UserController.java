package com.nonsyncbobbal.EcomApplication.controller;

import com.nonsyncbobbal.EcomApplication.dto.UserRequest;
import com.nonsyncbobbal.EcomApplication.dto.UserResponse;
import com.nonsyncbobbal.EcomApplication.model.User;
import com.nonsyncbobbal.EcomApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UserRequest userRequest){
        return  ResponseEntity.ok(userService.addUser(userRequest));

    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable int id) {
        if (userService.getUserById(id).isPresent()) {
            return ResponseEntity.ok(userService.getUserById(id).get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody UserRequest userRequest){
        boolean updated = userService.updateUser(id, userRequest);
        if (updated) {
            return ResponseEntity.ok("The user has been updated Successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
