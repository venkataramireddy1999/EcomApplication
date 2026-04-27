package com.nonsyncbobbal.EcomApplication.service;

import com.nonsyncbobbal.EcomApplication.dto.AddressDTO;
import com.nonsyncbobbal.EcomApplication.dto.UserRequest;
import com.nonsyncbobbal.EcomApplication.dto.UserResponse;
import com.nonsyncbobbal.EcomApplication.model.Address;
import com.nonsyncbobbal.EcomApplication.model.User;
import com.nonsyncbobbal.EcomApplication.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponse> getUsers() {

        return userRepository.findAll().stream().
                map(this::mapToUserResponse).
                collect(Collectors.toList());
    }

    public String addUser(UserRequest userRequest){
        User  user = new User();
        UpdateUserFromUserRequest(user, userRequest);
        userRepository.save(user);
        return "The user has been created successfully";
    }
    public Optional<UserResponse> getUserById(int id){
        return userRepository.findById(id)
                .map(this::mapToUserResponse);
    }

    public boolean updateUser(int id, UserRequest userRequest){

        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isPresent()){
            User user = userOptional.get();
            UpdateUserFromUserRequest(user, userRequest);
            userRepository.save(user);
            return true;
        }
        return false;

    }

    public void UpdateUserFromUserRequest(User user, UserRequest userRequest){
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        if(userRequest.getAddressDTO() != null){
            Address address = new Address();
            address.setStreet(userRequest.getAddressDTO().getStreet());
            address.setCity(userRequest.getAddressDTO().getCity());
            address.setState(userRequest.getAddressDTO().getState());
            address.setCountry(userRequest.getAddressDTO().getCountry());
            address.setZipcode(userRequest.getAddressDTO().getZipcode());
            user.setAddress(address);
        }
    }

    public UserResponse mapToUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(String.valueOf(user.getId()));
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setUserRole(user.getUserRole());
        if(user.getAddress() != null){
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            userResponse.setAddress(addressDTO);
        }
        return userResponse;

    }
}
