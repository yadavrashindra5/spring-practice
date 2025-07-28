package com.practice.services;

import com.practice.dtos.UserDto;

import java.util.List;

public interface UserService {
    UserDto create(UserDto userDto);

    UserDto delete(String userId);

    UserDto assignRoleToUser(String userId, String roleId);

    List<UserDto> allUser();
}
