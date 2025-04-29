package com.example.goodoc.service.impl;

import com.example.goodoc.dto.user.UserResponse;
import com.example.goodoc.exception.CustomException;
import com.example.goodoc.mapper.UserMapper;
import com.example.goodoc.repository.UserRepository;
import com.example.goodoc.service.MyUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MyUserServiceImpl implements MyUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public List<UserResponse> all() {
        return userMapper.toDtoS(userRepository.findAll());
    }

    @Override
    public UserResponse findById(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElseThrow(() -> new CustomException("User not found!", HttpStatus.NOT_FOUND)));
    }

    @Override
    public void deleteById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new CustomException("User not found!", HttpStatus.NOT_FOUND);
        }

    }
}
