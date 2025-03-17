package com.example.goodoc.dto.user;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String surname;
    private String number;
}
