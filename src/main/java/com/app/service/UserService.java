package com.app.service;


import com.app.dto.UserRequest;
import com.app.dto.UserResponse;
import com.app.mapper.MapperClass;
import com.app.model.Address;
import com.app.model.User;
import com.app.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;


    public UserResponse createUser(UserRequest userRequest){

        //DTO to entity
       User user = MapperClass.mapToUser(userRequest);

        User savedUser = userRepo.save(user);
        //entity to response
        return MapperClass.mapToUserResponse(savedUser);

    }


    public List<UserResponse> getAllUsers() {
      return userRepo.findAll().stream().map(MapperClass::mapToUserResponse).toList();
    }

    public UserResponse updateUser(String id, UserRequest userRequest) {

        Optional<User> optionalUser = userRepo.findById(Long.valueOf(id));

        if(optionalUser.isEmpty()){
        throw new RuntimeException("User not found");

        }

        User existingUser = optionalUser.get();
        existingUser.setName(userRequest.getName());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setPhone(userRequest.getPhone());

        Address existingAddress = existingUser.getAddress();

        if(existingAddress != null && userRequest.getAddress() != null) {
            existingAddress.setStreet(userRequest.getAddress().getStreet());
            existingAddress.setCity(userRequest.getAddress().getCity());
            existingAddress.setCountry(userRequest.getAddress().getCountry());
            existingAddress.setState(userRequest.getAddress().getState());
        }

        existingUser.setAddress(existingAddress);

        User savedUser = userRepo.save(existingUser);

        return MapperClass.mapToUserResponse(savedUser);

    }

    public void deleteUser(String id) {

        if(userRepo.existsById(Long.valueOf(id))){
            userRepo.deleteById(Long.valueOf(id));
        }else {
            throw new RuntimeException("User not found");
        }

    }
}
