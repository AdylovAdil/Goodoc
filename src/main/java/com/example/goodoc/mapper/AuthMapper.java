package com.example.goodoc.mapper;

import com.example.goodoc.dto.auth.AuthRegisterRequest;
import com.example.goodoc.dto.auth.AuthResponse;
import com.example.goodoc.model.User;

public interface AuthMapper {
    AuthResponse toDto(User user);

    User toDtoUser(AuthRegisterRequest request);
}
