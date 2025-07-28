package com.practice.services.impl;

import com.practice.dtos.RoleDto;
import com.practice.entities.Permission;
import com.practice.entities.Role;
import com.practice.exception.DuplicateDataException;
import com.practice.exception.ResourceNotFoundException;
import com.practice.repositories.PermissionRepository;
import com.practice.repositories.RoleRepository;
import com.practice.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoleDto create(RoleDto roleDto) {
        //set specific id to the role
        roleDto.setRoleId(UUID.randomUUID().toString());
        //change each character of Role to upper case
        roleDto.setRoleName(roleDto.getRoleName().trim().toUpperCase());

        //find if there is already role is created or not
        roleRepository.findByRoleName(roleDto.getRoleName()).ifPresent(role -> {
            throw new DuplicateDataException("This role is already created");
        });

        Role roleDtoToRole = modelMapper.map(roleDto, Role.class);

        Role savedRole = roleRepository.save(roleDtoToRole);
        return modelMapper.map(savedRole, RoleDto.class);
    }

    @Override
    public RoleDto delete(String roleId) {
        //find specific role
        Role providedResourceNotFound = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Give role id not found into the system"));

        //delete the id
        roleRepository.delete(providedResourceNotFound);

        return modelMapper.map(providedResourceNotFound, RoleDto.class);
    }

    @Override
    public RoleDto getRole(String roleId) {
        Role roleWithId = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Give role id not found into the system"));

        return modelMapper.map(roleWithId, RoleDto.class);
    }

    @Override
    public RoleDto assignPermissionToRole(String roleId, String permissionId) {

        Role roleWithId = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Give role id not found into the system"));

        Permission permission = permissionRepository.findById(permissionId).orElseThrow(() -> new ResourceNotFoundException("Given permission id not found into the systme"));

        roleWithId.getPermissionList().add(permission);

        Role savedRole = roleRepository.save(roleWithId);

        return modelMapper.map(savedRole, RoleDto.class);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> all = roleRepository.findAll();
        List<RoleDto> collect = all.stream().map(role -> modelMapper.map(role, RoleDto.class)).collect(Collectors.toList());
        return collect;
    }
}
