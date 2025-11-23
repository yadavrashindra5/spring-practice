package com.practice.services;

import com.practice.entities.User;
import com.practice.response.PageableResponse;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User getUser(String userId);

    PageableResponse<User> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir);

    User delete(String userId);

    User assignRole(String userId, String roleId);
}
