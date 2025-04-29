package com.example.goodoc.dto.auth;

import lombok.Data;

@Data
public class AuthRegisterRequest {
    private String number;
    private String password;
    private String confirmPassword;
}
