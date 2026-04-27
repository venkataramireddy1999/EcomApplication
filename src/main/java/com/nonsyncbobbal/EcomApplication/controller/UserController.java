package com.nonsyncbobbal.EcomApplication.controller;

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
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user){
        return  ResponseEntity.ok(userService.addUser(user));

    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        if (userService.getUserById(id).isPresent()) {
            return ResponseEntity.ok(userService.getUserById(id).get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody User user){
        if (userService.getUserById(id).isPresent()) {
            return ResponseEntity.ok(userService.addUser(user));
        }
        return ResponseEntity.notFound().build();
    }
}
