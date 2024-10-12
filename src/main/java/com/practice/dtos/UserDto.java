package com.practice.dtos;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDto {
    private String id;
    private String name;
    private String address;
    private String phone;
}
