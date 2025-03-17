package com.example.goodoc.mapper;

import com.example.goodoc.dto.reception.ReceptionRequest;
import com.example.goodoc.dto.reception.ReceptionResponse;
import com.example.goodoc.model.Reception;

import java.util.List;

public interface ReceptionMapper {
    ReceptionResponse toDto(Reception reception);

    List<ReceptionResponse> toDtoS(List<Reception> receptions);

    Reception toDtoReception(Reception reception, ReceptionRequest receptionRequest);
}
