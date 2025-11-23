package com.practice.dtos;

import com.practice.entities.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {
    private String token;
    User user;
    private String refreshToken;
}
