package com.practice.services;

import com.practice.entities.Role;

import java.util.List;

public interface RoleService {
    Role createRole(Role role);
    Role deleteRole(String roleId);
    List<Role>getAllRole();
}
