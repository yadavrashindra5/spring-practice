package com.practice.services.impl;

import com.practice.dtos.UserDto;
import com.practice.entities.Assets;
import com.practice.entities.Role;
import com.practice.entities.User;
import com.practice.exception.DuplicateDataException;
import com.practice.exception.ResourceNotFoundException;
import com.practice.repositories.AssetsRepository;
import com.practice.repositories.RoleRepository;
import com.practice.repositories.UserRepository;
import com.practice.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AssetsRepository assetsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto create(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.findByUserEmail(userDto.getUserEmail().trim()).ifPresent(user -> {
            throw new DuplicateDataException("User is already created");
        });

        User user = modelMapper.map(userDto, User.class);
        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto delete(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepository.delete(user);

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto assignRoleToUser(String userId, String roleId) {

        Role providedRoleDoesNotFound = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("provided role does not found"));

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.getRoles().add(providedRoleDoesNotFound);

        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public List<UserDto> allUser() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public UserDto assignAssetToUser(String userId, String assetId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Assets assets = assetsRepository.findById(assetId).orElseThrow(() -> new ResourceNotFoundException("Given assets not found"));

        assets.setUser(user);

        user.setAssets(assets);

        User savedUser = userRepository.save(user);

        assetsRepository.save(assets);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return modelMapper.map(user, UserDto.class);
    }
}
