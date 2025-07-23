package com.practice.services;

import com.practice.dtos.PermissionDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PermissionService {
    PermissionDto create(PermissionDto permissionDto);

    PermissionDto delete(String permissionId);

    List<PermissionDto> getAllPermission();
}
