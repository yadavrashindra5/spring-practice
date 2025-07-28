package com.practice.controllers;

import com.practice.dtos.UserDto;
import com.practice.response.ApiResponse;
import com.practice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody UserDto userDto) {
        UserDto userDto1 = userService.create(userDto);
        ApiResponse userCreated = ApiResponse.builder().message("user created").data(userDto1).status(HttpStatus.CREATED.value()).build();
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String userId) {
        UserDto userDto1 = userService.delete(userId);

        ApiResponse userCreated = ApiResponse.builder().message("user deleted").data(userDto1).status(HttpStatus.OK.value()).build();
        return new ResponseEntity<>(userCreated, HttpStatus.OK);
    }

    @PatchMapping("/{userId}/{roleId}")
    public ResponseEntity<ApiResponse> assignRoleToUser(@PathVariable String userId, @PathVariable String roleId) {
        UserDto userDto1 = userService.assignRoleToUser(userId, roleId);
        ApiResponse userCreated = ApiResponse.builder().message("assign role to user").data(userDto1).status(HttpStatus.OK.value()).build();
        return new ResponseEntity<>(userCreated, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<UserDto> userDtoList = userService.allUser();
        ApiResponse allUserData = ApiResponse.builder().status(HttpStatus.OK.value()).data(userDtoList).message("all user data").build();
        return new ResponseEntity<>(allUserData, HttpStatus.OK);
    }

    @PatchMapping("/asset/{userId}/{assetId}")
    public ResponseEntity<ApiResponse> assignAssetToUser(@PathVariable String userId, @PathVariable String assetId) {
        UserDto userDto1 = userService.assignAssetToUser(userId, assetId);
        ApiResponse userCreated = ApiResponse.builder().message("assign role to user").data(userDto1).status(HttpStatus.OK.value()).build();
        return new ResponseEntity<>(userCreated, HttpStatus.OK);
    }

}
