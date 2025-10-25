package com.practice.services.impl;

import com.practice.dao.UserDao;
import com.practice.entities.User;
import com.practice.repositories.UserRepository;
import com.practice.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDao create(UserDao userDao) {
        userDao.setUserId(UUID.randomUUID().toString());
        userDao.setPassword(passwordEncoder.encode(userDao.getPassword()));
        User map = modelMapper.map(userDao, User.class);
        User save = userRepository.save(map);
        return modelMapper.map(save, UserDao.class);
    }

    @Override
    public UserDao get(String phoneNumber) {
        User byPhoneNumber = userRepository.findByPhoneNumber(phoneNumber);
        return modelMapper.map(byPhoneNumber, UserDao.class);
    }
}
