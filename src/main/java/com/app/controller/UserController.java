package com.app.controller;


import com.app.dto.UserRequest;
import com.app.dto.UserResponse;
import com.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        List<UserResponse> allUsers = userService.getAllUsers();

        return new ResponseEntity<>(allUsers, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody UserRequest userRequest) {

        Map<String, Object> response = new HashMap<>();

        UserResponse user = userService.createUser(userRequest);
            response.put("user", user);
            response.put("status", HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String , Object>> updateUser(@PathVariable String id ,@RequestBody UserRequest userRequest) {

        Map<String , Object> response = new HashMap<>();

      UserResponse updateUser =   userService.updateUser(id , userRequest);

      response.put("user", updateUser);
      response.put("status", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {

         userService.deleteUser(id);
         return new ResponseEntity<>(HttpStatus.OK);
    }
}
