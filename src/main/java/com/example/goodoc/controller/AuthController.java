package com.example.goodoc.controller;

import com.example.goodoc.dto.auth.AuthLoginRequest;
import com.example.goodoc.dto.auth.AuthRegisterRequest;
import com.example.goodoc.dto.auth.AuthResponse;
import com.example.goodoc.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRegisterRequest authRegisterRequest) {
        return authService.register(authRegisterRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthLoginRequest authLoginRequest) {
        return authService.login(authLoginRequest);
    }
}
