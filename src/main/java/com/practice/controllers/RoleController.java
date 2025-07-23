package com.practice.controllers;

import com.practice.dtos.RoleDto;
import com.practice.response.ApiResponse;
import com.practice.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<ApiResponse<RoleDto>> create(@RequestBody @Valid RoleDto roleDto) {
        RoleDto roleDto1 = roleService.create(roleDto);
        ApiResponse newRoleIsCreated = ApiResponse.builder().status(HttpStatus.CREATED.value()).message("New Role is created").data(roleDto1).build();
        return new ResponseEntity<ApiResponse<RoleDto>>(newRoleIsCreated, HttpStatus.CREATED);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<ApiResponse<RoleDto>> delete(@PathVariable String roleId) {
        RoleDto roleDto1 = roleService.delete(roleId);
        ApiResponse newRoleIsCreated = ApiResponse.builder().status(HttpStatus.OK.value()).message("This role is deleted").data(roleDto1).build();
        return new ResponseEntity<ApiResponse<RoleDto>>(newRoleIsCreated, HttpStatus.OK);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<ApiResponse<RoleDto>> getRole(@PathVariable String roleId) {
        RoleDto roleDto1 = roleService.getRole(roleId);
        ApiResponse newRoleIsCreated = ApiResponse.builder().status(HttpStatus.OK.value()).message("Given specific role").data(roleDto1).build();
        return new ResponseEntity<ApiResponse<RoleDto>>(newRoleIsCreated, HttpStatus.OK);
    }

    @PatchMapping("/{roleId}/{permissionId}")
    public ResponseEntity<ApiResponse<RoleDto>> assignPermissionToRole(@PathVariable String roleId, @PathVariable String permissionId) {
        RoleDto roleDto = roleService.assignPermissionToRole(roleId, permissionId);
        ApiResponse perssionIsAddedToRole = ApiResponse.builder().status(HttpStatus.OK.value()).data(roleDto).message("perssion is added to role").build();
        return new ResponseEntity<>(perssionIsAddedToRole, HttpStatus.OK);
    }
}
