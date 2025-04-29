package com.example.goodoc.service.impl;

import com.example.goodoc.dto.auth.AuthLoginRequest;
import com.example.goodoc.dto.auth.AuthRegisterRequest;
import com.example.goodoc.dto.auth.AuthResponse;
import com.example.goodoc.exception.CustomException;
import com.example.goodoc.mapper.AuthMapper;
import com.example.goodoc.model.User;
import com.example.goodoc.repository.UserRepository;
import com.example.goodoc.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(AuthRegisterRequest authRegisterRequest) {
        if (!authRegisterRequest.getPassword().equals(authRegisterRequest.getConfirmPassword())) {
            throw new CustomException("Password is not same", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.findByNumber(authRegisterRequest.getNumber()).isPresent()) {
            throw new CustomException("User is found", HttpStatus.FOUND);
        }
        User user = authMapper.toDtoUser(authRegisterRequest);
        userRepository.save(user);
        return authMapper.toDto(user);
    }

    @Override
    public AuthResponse login(AuthLoginRequest authLoginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authLoginRequest.getNumber(),
                        authLoginRequest.getPassword()
                )
        );
        User user = userRepository.findByNumber(authLoginRequest.getNumber()).orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
        return authMapper.toDto(user);
    }
}
