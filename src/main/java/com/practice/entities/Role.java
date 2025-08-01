package com.practice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Role {
    @Id
    private String roleId;
    private String roleName;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private List<Permission> permissionList = new ArrayList<>();
}
