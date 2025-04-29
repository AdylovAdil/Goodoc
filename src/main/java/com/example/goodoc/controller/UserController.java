package com.example.goodoc.controller;

import com.example.goodoc.dto.user.UserResponse;
import com.example.goodoc.service.MyUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    private final MyUserService userService;

    @GetMapping("/all")
    public List<UserResponse> all() {
        return userService.all();
    }

    @GetMapping("/findById/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
