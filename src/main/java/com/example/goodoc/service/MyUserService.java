package com.example.goodoc.service;

import com.example.goodoc.dto.user.UserResponse;

import java.util.List;

public interface MyUserService {
    List<UserResponse> all();

    UserResponse findById(Long id);

    void deleteById(Long id);
}
