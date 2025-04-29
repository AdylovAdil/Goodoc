package com.example.goodoc.mapper.impl;

import com.example.goodoc.config.JwtService;
import com.example.goodoc.dto.auth.AuthRegisterRequest;
import com.example.goodoc.dto.auth.AuthResponse;
import com.example.goodoc.enums.Role;
import com.example.goodoc.mapper.AuthMapper;
import com.example.goodoc.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthMapperImpl implements AuthMapper {
    private final JwtService jwtService;
    private final PasswordEncoder encoder;

    @Override
    public AuthResponse toDto(User user) {
        AuthResponse authResponse = new AuthResponse();
        String token = jwtService.generateToken(user);
        authResponse.setToken(token);
        return authResponse;
    }

    @Override
    public User toDtoUser(AuthRegisterRequest request) {
        User user = new User();
        user.setNumber(request.getNumber());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        return user;
    }
}
