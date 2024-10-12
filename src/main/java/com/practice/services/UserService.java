package com.practice.services;

import com.practice.dtos.UserDto;
import com.practice.response.PageableResponse;

import java.util.List;

public interface UserService {
    UserDto save(UserDto userDto);

    PageableResponse<UserDto> get(int pageNumber, int pageSize, String sortBy, String sortDir);
}
