package com.example.goodoc.controller;

import com.example.goodoc.dto.reception.ReceptionRequest;
import com.example.goodoc.dto.reception.ReceptionResponse;
import com.example.goodoc.service.ReceptionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReceptionController.class)
@ExtendWith(MockitoExtension.class)
class ReceptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReceptionService receptionService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void all() throws Exception {
        List<ReceptionResponse> responseList = Collections.emptyList();
        when(receptionService.all()).thenReturn(responseList);

        mockMvc.perform(MockMvcRequestBuilders.get("/reception/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseList)));
    }

    @Test
    void findById() throws Exception {
        Long id = 1L;
        ReceptionResponse response = new ReceptionResponse();
        response.setId(id);
        response.setName("Morning Reception");

        when(receptionService.findById(id)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/reception/findById/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    void updateById() throws Exception {
        Long id = 1L;
        ReceptionRequest request = new ReceptionRequest();
        request.setName("Updated Reception");

        mockMvc.perform(MockMvcRequestBuilders.put("/reception/updateById/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(receptionService, times(1)).updateById(eq(id), any(ReceptionRequest.class));
    }

    @Test
    void deleteById() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/reception/deleteById/{id}", id))
                .andExpect(status().isOk());

        verify(receptionService, times(1)).deleteById(id);
    }

    @Test
    void create() throws Exception {
        ReceptionRequest request = new ReceptionRequest();
        request.setName("New Reception");

        mockMvc.perform(MockMvcRequestBuilders.post("/reception/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(receptionService, times(1)).create(any(ReceptionRequest.class));
    }
}
