package com.example.goodoc.dto.user;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String surname;
    private String number;
}
