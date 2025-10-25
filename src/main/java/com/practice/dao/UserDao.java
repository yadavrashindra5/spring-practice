package com.practice.dao;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDao {
    private String userId;
    private String phoneNumber;
    private String password;
    private String userName;
    private List<String> roles = new ArrayList<>();
}
