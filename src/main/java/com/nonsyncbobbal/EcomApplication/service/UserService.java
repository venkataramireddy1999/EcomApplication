package com.nonsyncbobbal.EcomApplication.service;

import com.nonsyncbobbal.EcomApplication.model.User;
import com.nonsyncbobbal.EcomApplication.repo.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //List<User> users = new ArrayList<>(List.of(new User(1, "Venkata", "Ramireddy"),
    //                                           new User(2, "Nikolay", "Nikolay")));

    public List<User> getUsers() {
        return userRepository.findAll();
    }
    public String addUser(User user){
        userRepository.save(user);
        return "User added successfully";
    }
    public Optional<User> getUserById(int id){
        return userRepository.findById(id);
    }
}
