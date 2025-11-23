package com.practice.controllers;

import com.practice.entities.Role;
import com.practice.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        role.setRoleId(UUID.randomUUID().toString());
        Role role1 = roleService.createRole(role);
        return new ResponseEntity<>(role1, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{roleId}")
    public ResponseEntity<Role> deleteRole(@PathVariable String roleId) {
        Role role = roleService.deleteRole(roleId);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping
    public ResponseEntity<List<Role>> getAllRole() {
        List<Role> allRole = roleService.getAllRole();
        return new ResponseEntity<>(allRole, HttpStatus.OK);
    }
}
