package com.practice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class User {
    @Id
    private String id;
    private String name;
    private String address;
    private String phone;
}
