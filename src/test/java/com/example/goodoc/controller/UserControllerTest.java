package com.example.goodoc.controller;

import com.example.goodoc.dto.user.UserRequest;
import com.example.goodoc.dto.user.UserResponse;
import com.example.goodoc.service.MyUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyUserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private UserResponse mockUserResponse;
    private UserRequest mockUserRequest;

    @BeforeEach
    void setUp() {
        mockUserResponse = new UserResponse();
        mockUserResponse.setId(1L);
        mockUserResponse.setName("John");
        mockUserResponse.setEmail("john@example.com");
        mockUserResponse.setSurname("Doe");
        mockUserResponse.setNumber("123456789");

        mockUserRequest = new UserRequest();
        mockUserRequest.setName("John");
        mockUserRequest.setEmail("john@example.com");
        mockUserRequest.setSurname("Doe");
        mockUserRequest.setNumber("123456789");
    }

    @Test
    void all() throws Exception {
        List<UserResponse> users = Collections.singletonList(mockUserResponse);

        when(userService.all()).thenReturn(users);

        mockMvc.perform(get("/user/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("John"));

        verify(userService, times(1)).all();
    }

    @Test
    void findById() throws Exception {
        when(userService.findById(1L)).thenReturn(mockUserResponse);

        mockMvc.perform(get("/user/findById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"));

        verify(userService, times(1)).findById(1L);
    }

    @Test
    void deleteById() throws Exception {
        doNothing().when(userService).deleteById(1L);

        mockMvc.perform(delete("/user/deleteById/1"))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteById(1L);
    }

    @Test
    void save() throws Exception {
        when(userService.save(any(UserRequest.class))).thenReturn(mockUserResponse);

        mockMvc.perform(post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockUserRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"));

        verify(userService, times(1)).save(any(UserRequest.class));
    }
}
