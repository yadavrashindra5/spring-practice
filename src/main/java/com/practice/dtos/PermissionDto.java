package com.practice.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionDto {
    private String permissionId;
    @NotNull(message = "This value should not be null")
    @NotEmpty(message = "This should not be empty")
    private String permissionTitle;
}
