package com.practice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
    @OneToMany
    @JoinColumn(name = "role_id")
    private List<Permission> permissionList = new ArrayList<>();
}
