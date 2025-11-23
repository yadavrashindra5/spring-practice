package com.practice.controllers;

import com.practice.entities.User;
import com.practice.response.PageableResponse;
import com.practice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user) {
        User userCreated = userService.createUser(user);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        User user = userService.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<PageableResponse<User>> getAllUsers(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        PageableResponse<User> allUsers = userService.getAllUsers(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUser(String userId) {
        User deletedUser = userService.delete(userId);
        return new ResponseEntity<>(deletedUser, HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @PutMapping("/{userId}/{roleId}")
    public ResponseEntity<User> assignRole(@PathVariable String userId, @PathVariable String roleId) {
        User user = userService.assignRole(userId, roleId);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
