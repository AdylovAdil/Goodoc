package com.example.goodoc.dto.auth;

import lombok.Data;

@Data
public class AuthLoginRequest {
    private String number;
    private String password;
}
