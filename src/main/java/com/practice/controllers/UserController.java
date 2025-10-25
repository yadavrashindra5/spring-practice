package com.practice.controllers;

import com.practice.dao.UserDao;
import com.practice.services.UserService;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Base64;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<UserDao> create(@RequestBody UserDao userDao) {

        UserDao userDao1 = userService.create(userDao);
        return new ResponseEntity<>(userDao1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<UserDao> get(String phoneNumber) {
        UserDao userDao = userService.get(phoneNumber);
        return new ResponseEntity<>(userDao, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test() {
        SecretKey secretKey = Jwts.SIG.HS512.key().build();
        String base64Key = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        logger.info("Your new Base64 Secret Key: {}", base64Key);
        return "Hello world";
    }
}
