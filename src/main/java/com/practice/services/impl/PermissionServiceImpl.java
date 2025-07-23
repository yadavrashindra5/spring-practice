package com.practice.services.impl;

import com.practice.dtos.PermissionDto;
import com.practice.entities.Permission;
import com.practice.exception.DuplicateDataException;
import com.practice.exception.ResourceNotFoundException;
import com.practice.repositories.PermissionRepository;
import com.practice.services.PermissionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PermissionDto create(PermissionDto permissionDto) {
        permissionDto.setPermissionId(UUID.randomUUID().toString());
        permissionDto.setPermissionTitle(permissionDto.getPermissionTitle().toLowerCase());
        Permission permission1 = permissionRepository.findByPermissionTitle(permissionDto.getPermissionTitle().toLowerCase()).orElse(null);
        if (permission1 != null) {
            throw new DuplicateDataException("This permission was already added");
        }
        Permission permission = modelMapper.map(permissionDto, Permission.class);
        Permission savedPermission = permissionRepository.save(permission);
        return modelMapper.map(savedPermission, PermissionDto.class);
    }

    @Override
    public PermissionDto delete(String permissionId) {
        Permission permission = permissionRepository.findById(permissionId).orElseThrow(() -> new ResourceNotFoundException("Provided permission id not found"));
        permissionRepository.delete(permission);
        return modelMapper.map(permission, PermissionDto.class);
    }

    @Override
    public List<PermissionDto> getAllPermission() {
        List<Permission> permissionList = permissionRepository.findAll();
        return permissionList.stream().map(permission -> modelMapper.map(permission, PermissionDto.class)).collect(Collectors.toList());
    }
}
