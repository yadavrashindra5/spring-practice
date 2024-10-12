package com.practice.controllers;

import com.practice.dtos.UserDto;
import com.practice.response.PageableResponse;
import com.practice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.save(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<UserDto>> get(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        PageableResponse<UserDto> userDtoPageableResponse = userService.get(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(userDtoPageableResponse, HttpStatus.OK);
    }
}
