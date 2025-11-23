package com.practice.services.impl;

import com.practice.entities.Role;
import com.practice.exceptions.UserNotFoundException;
import com.practice.repositories.RoleRepository;
import com.practice.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        Role save = roleRepository.save(role);
        return save;
    }

    @Override
    public Role deleteRole(String roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new UserNotFoundException("User not found"));
        roleRepository.delete(role);
        return role;
    }

    @Override
    public List<Role> getAllRole() {
        List<Role> roleList = roleRepository.findAll();
        return roleList;
    }
}
