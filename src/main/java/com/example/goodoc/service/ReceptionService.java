package com.example.goodoc.service;

import com.example.goodoc.dto.reception.ReceptionRequest;
import com.example.goodoc.dto.reception.ReceptionResponse;

import java.util.List;

public interface ReceptionService {
    List<ReceptionResponse> all();

    ReceptionResponse findById(Long id);

    void updateById(Long id, ReceptionRequest receptionRequest);

    void deleteById(Long id);

    void create(ReceptionRequest request);
}
