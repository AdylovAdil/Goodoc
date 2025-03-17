package com.example.goodoc.mapper.impl;

import com.example.goodoc.dto.reception.ReceptionRequest;
import com.example.goodoc.dto.reception.ReceptionResponse;
import com.example.goodoc.mapper.ReceptionMapper;
import com.example.goodoc.model.Reception;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReceptionMapperImpl implements ReceptionMapper {
    @Override
    public ReceptionResponse toDto(Reception reception) {
        ReceptionResponse receptionResponse = new ReceptionResponse();
        receptionResponse.setId(reception.getId());
        receptionResponse.setName(reception.getName());
        return receptionResponse;
    }

    @Override
    public List<ReceptionResponse> toDtoS(List<Reception> receptions) {
        List<ReceptionResponse> receptionResponses = new ArrayList<>();
        for (Reception reception : receptions) {
            receptionResponses.add(toDto(reception));
        }
        return receptionResponses;
    }

    @Override
    public Reception toDtoReception(Reception reception, ReceptionRequest receptionRequest) {
        reception.setName(receptionRequest.getName());
        return reception;
    }
}
