package com.practice.controllers;

import com.practice.dtos.PermissionDto;
import com.practice.response.ApiResponse;
import com.practice.services.PermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @PostMapping
    public ResponseEntity<ApiResponse<PermissionDto>> create(@RequestBody @Valid PermissionDto permissionDto) {
        PermissionDto permissionDto1 = permissionService.create(permissionDto);
        ApiResponse<PermissionDto> permissionIsCreated = new ApiResponse<>();
        permissionIsCreated.setMessage("PERMISSION IS CREATED");
        permissionIsCreated.setData(permissionDto1);
        permissionIsCreated.setStatus(HttpStatus.CREATED.value());
        return new ResponseEntity<>(permissionIsCreated, HttpStatus.CREATED);
    }

    @DeleteMapping("/{permissionId}")
    public ResponseEntity<ApiResponse<PermissionDto>> delete(@PathVariable String permissionId) {
        PermissionDto delete = permissionService.delete(permissionId);
        ApiResponse<PermissionDto> permissionDtoApiResponse = new ApiResponse<>();
        permissionDtoApiResponse.setStatus(HttpStatus.OK.value());
        permissionDtoApiResponse.setMessage("Permission is deleted");
        permissionDtoApiResponse.setData(delete);
        return new ResponseEntity<>(permissionDtoApiResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PermissionDto>> getAllPermissions() {
        List<PermissionDto> allPermission = permissionService.getAllPermission();
        ApiResponse allPermission1 = ApiResponse.builder().status(HttpStatus.OK.value()).message("All permission").data(allPermission).build();
        return new ResponseEntity<>(allPermission1, HttpStatus.OK);
    }
}
