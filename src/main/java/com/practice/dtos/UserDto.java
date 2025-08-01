package com.practice.dtos;

import com.practice.entities.Assets;
import com.practice.entities.Role;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String userId;
    private String userName;
    private String userEmail;
    private String password;
    private List<RoleDto> roles = new ArrayList<>();
    private Assets assets;
}
