package com.example.goodoc.controller;

import com.example.goodoc.dto.medicine.MedicineRequest;
import com.example.goodoc.dto.medicine.MedicineResponse;
import com.example.goodoc.service.MedicineService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MedicineController.class)
@ExtendWith(MockitoExtension.class)
class MedicineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicineService medicineService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void all() throws Exception {
        List<MedicineResponse> responseList = Collections.emptyList();
        when(medicineService.all()).thenReturn(responseList);

        mockMvc.perform(MockMvcRequestBuilders.get("/medicine/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseList)));
    }

    @Test
    void findById() throws Exception {
        Long id = 1L;
        MedicineResponse response = new MedicineResponse();
        response.setName("Paracetamol");

        when(medicineService.findById(id)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/medicine/findById/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    void updateById() throws Exception {
        Long id = 1L;
        MedicineRequest request = new MedicineRequest();
        request.setName("Ibuprofen");

        mockMvc.perform(MockMvcRequestBuilders.put("/medicine/updateById/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(medicineService, times(1)).updateById(eq(id), any(MedicineRequest.class));
    }

    @Test
    void deleteById() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/medicine/deleteById/{id}", id))
                .andExpect(status().isOk());

        verify(medicineService, times(1)).deleteById(id);
    }

    @Test
    void addMedicine() throws Exception {
        MedicineRequest request = new MedicineRequest();
        request.setName("Aspirin");

        mockMvc.perform(MockMvcRequestBuilders.post("/medicine/addMedicine")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(medicineService, times(1)).addMedicine(any(MedicineRequest.class));
    }
}
