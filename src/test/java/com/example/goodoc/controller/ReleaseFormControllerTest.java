package com.example.goodoc.controller;

import com.example.goodoc.dto.releaseForm.ReleaseFormRequest;
import com.example.goodoc.dto.releaseForm.ReleaseFormResponse;
import com.example.goodoc.service.ReleaseFormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;

@WebMvcTest(ReleaseFormController.class)
class ReleaseFormControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReleaseFormService releaseFormService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private ReleaseFormResponse releaseFormResponse;

    @BeforeEach
    void setUp() {
        releaseFormResponse = new ReleaseFormResponse();
        releaseFormResponse.setId(1L);
        releaseFormResponse.setName("Test Form");
    }

    @Test
    void all() throws Exception {
        List<ReleaseFormResponse> responseList = Collections.singletonList(releaseFormResponse);
        when(releaseFormService.all()).thenReturn(responseList);

        mockMvc.perform(get("/releaseForm/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Test Form"));
    }

    @Test
    void findById() throws Exception {
        when(releaseFormService.findById(1L)).thenReturn(releaseFormResponse);

        mockMvc.perform(get("/releaseForm/findById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Form"));
    }

    @Test
    void updateById() throws Exception {
        ReleaseFormRequest request = new ReleaseFormRequest();
        request.setName("Updated Form");

        mockMvc.perform(put("/releaseForm/updateById/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(releaseFormService, times(1)).updateById(eq(1L), any(ReleaseFormRequest.class));
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/releaseForm/deleteById/1"))
                .andExpect(status().isOk());

        verify(releaseFormService, times(1)).deleteById(1L);
    }

    @Test
    void create() throws Exception {
        ReleaseFormRequest request = new ReleaseFormRequest();
        request.setName("New Form");

        mockMvc.perform(post("/releaseForm/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(releaseFormService, times(1)).create(any(ReleaseFormRequest.class));
    }
}