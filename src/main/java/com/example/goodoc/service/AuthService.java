package com.example.goodoc.service;

import com.example.goodoc.dto.auth.AuthLoginRequest;
import com.example.goodoc.dto.auth.AuthRegisterRequest;
import com.example.goodoc.dto.auth.AuthResponse;

public interface AuthService {
    AuthResponse register(AuthRegisterRequest authRegisterRequest);

    AuthResponse login(AuthLoginRequest authLoginRequest);
}
