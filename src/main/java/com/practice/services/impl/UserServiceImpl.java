package com.practice.services.impl;

import com.practice.entities.Role;
import com.practice.entities.User;
import com.practice.exceptions.UserNotFoundException;
import com.practice.helper.Helper;
import com.practice.repositories.RoleRepository;
import com.practice.repositories.UserRepository;
import com.practice.response.PageableResponse;
import com.practice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Provided User id not found"));
        return user;
    }

    @Override
    public PageableResponse<User> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<User> page = userRepository.findAll(pageable);
        PageableResponse<User> pageableResponse = Helper.getPageableResponse(page, User.class);
        return pageableResponse;
    }

    @Override
    public User delete(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Provided user not found"));
        userRepository.delete(user);
        return user;
    }

    @Override
    public User assignRole(String userId, String roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Provided User id not found"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new UserNotFoundException("Provided roleId not found"));
        List<Role> roles = user.getRoles();
        roles.add(role);
        user.setRoles(roles);
        return userRepository.save(user);
    }
}
