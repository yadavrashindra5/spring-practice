package com.practice.services.impl;

import com.practice.entities.User;
import com.practice.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User byPhoneNumber = userRepository.findByPhoneNumber(phoneNumber);
        logger.info("this is logger");
        return byPhoneNumber;
    }
}
