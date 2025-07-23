package com.practice.dtos;

import com.practice.entities.Permission;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDto {
    private String roleId;
    @NotNull
    @NotEmpty
    private String roleName;
    private List<Permission> permissionList = new ArrayList<>();
}
