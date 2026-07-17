package com.nonsyncbobbal.EcomApplication.service;

import com.nonsyncbobbal.EcomApplication.dto.UserRequest;
import com.nonsyncbobbal.EcomApplication.dto.UserResponse;
import com.nonsyncbobbal.EcomApplication.mapper.UserMapper;
import com.nonsyncbobbal.EcomApplication.model.User;
import com.nonsyncbobbal.EcomApplication.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.nonsyncbobbal.EcomApplication.mapper.UserMapper.updateUserFromUserRequest;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponse> getUsers() {

        return userRepository.findAll().stream().
                map(UserMapper::mapToUserResponse).
                collect(Collectors.toList());
    }

    public String addUser(UserRequest userRequest){
        User  user = new User();
        UserMapper.updateUserFromUserRequest(user, userRequest);
        userRepository.save(user);
        return "The user has been created successfully";
    }
    public Optional<UserResponse> getUserById(int id){
        return userRepository.findById(id)
                .map(UserMapper::mapToUserResponse);
    }

    public boolean updateUser(int id, UserRequest userRequest){

        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isPresent()){
            User user = userOptional.get();
            UserMapper.updateUserFromUserRequest(user, userRequest);
            userRepository.save(user);
            return true;
        }
        return false;

    }


}
