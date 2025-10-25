package com.practice.dtos;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {
    private String token;
    UserDetails user;
    private String refreshToken;
}
