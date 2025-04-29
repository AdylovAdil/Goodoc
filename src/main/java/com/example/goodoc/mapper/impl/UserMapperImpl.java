package com.example.goodoc.mapper.impl;

import com.example.goodoc.dto.user.UserRequest;
import com.example.goodoc.dto.user.UserResponse;
import com.example.goodoc.mapper.UserMapper;
import com.example.goodoc.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserResponse toDto(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setNumber(user.getNumber());
        userResponse.setPassword(user.getPassword());
        return userResponse;
    }

    @Override
    public List<UserResponse> toDtoS(List<User> userList) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : userList) {
            userResponses.add(toDto(user));
        }
        return userResponses;
    }

    @Override
    public User toDtoUser(User user, UserRequest userRequest) {
        user.setNumber(userRequest.getNumber());
        user.setPassword(userRequest.getPassword());
        return user;
    }
}
