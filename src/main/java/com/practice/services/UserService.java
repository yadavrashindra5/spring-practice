package com.practice.services;

import com.practice.dao.UserDao;

public interface UserService {
    UserDao create(UserDao userDao);

    UserDao get(String phoneNumber);
}
