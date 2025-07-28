package com.practice.services;

import com.practice.dtos.RoleDto;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    RoleDto create(RoleDto roleDto);

    RoleDto delete(String roleId);

    RoleDto getRole(String roleId);

    RoleDto assignPermissionToRole(String roleId, String permissionId);

    List<RoleDto> getAllRoles();
}
