package com.practice.controllers;

import com.practice.dtos.JwtRequest;
import com.practice.dtos.JwtResponse;
import com.practice.entities.User;
import com.practice.security.jwt.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;


    @PostMapping("/generate-token")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        String userName = request.getUserName();
        String password = request.getPassword();
        this.doAuthenticate(userName, password);

        User user = (User) userDetailsService.loadUserByUsername(userName);

        String token = jwtHelper.generateToken(user);
        JwtResponse build = JwtResponse.builder().token(token).user(user).build();
        return ResponseEntity.ok(build);
    }


    private void doAuthenticate(String userName, String password) {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(userName, password);
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Invalid username and password");
        }
    }
}
