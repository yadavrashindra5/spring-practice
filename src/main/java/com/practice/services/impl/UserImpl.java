package com.practice.services.impl;

import com.practice.Helper.PageHelper;
import com.practice.dtos.UserDto;
import com.practice.entities.User;
import com.practice.repositories.UserRepository;
import com.practice.response.PageableResponse;
import com.practice.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDto save(UserDto userDto) {
        String id = UUID.randomUUID().toString();
        userDto.setId(id);
        User user = mapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
        return mapper.map(savedUser, UserDto.class);
    }

    @Override
    public PageableResponse<UserDto> get(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<User> page = userRepository.findAll(pageable);
        PageableResponse<UserDto> pageHelper = PageHelper.getPageableResponse(page, UserDto.class);
        return pageHelper;
    }
}
