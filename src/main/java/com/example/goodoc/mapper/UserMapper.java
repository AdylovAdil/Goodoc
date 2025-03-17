package com.example.goodoc.mapper;

import com.example.goodoc.dto.user.UserRequest;
import com.example.goodoc.dto.user.UserResponse;
import com.example.goodoc.model.User;

import java.util.List;

public interface UserMapper {
    UserResponse toDto(User user);

    List<UserResponse> toDtoS(List<User> userList);

    User toDtoUser(User user, UserRequest userRequest);
}
