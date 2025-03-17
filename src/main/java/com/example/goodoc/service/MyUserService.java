package com.example.goodoc.service;

import com.example.goodoc.dto.user.UserRequest;
import com.example.goodoc.dto.user.UserResponse;

import java.util.List;

public interface MyUserService {
    List<UserResponse> all();

    UserResponse findById(Long id);

    void deleteById(Long id);

    UserResponse save(UserRequest userRequest);
}
