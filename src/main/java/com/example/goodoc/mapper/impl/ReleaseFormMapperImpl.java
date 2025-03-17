package com.example.goodoc.mapper.impl;

import com.example.goodoc.dto.releaseForm.ReleaseFormRequest;
import com.example.goodoc.dto.releaseForm.ReleaseFormResponse;
import com.example.goodoc.mapper.ReleaseFormMapper;
import com.example.goodoc.model.ReleaseForm;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReleaseFormMapperImpl implements ReleaseFormMapper {

    @Override
    public ReleaseFormResponse toDto(ReleaseForm releaseForm) {
        ReleaseFormResponse releaseFormResponse = new ReleaseFormResponse();
        releaseFormResponse.setId(releaseForm.getId());
        releaseFormResponse.setName(releaseForm.getName());
        return releaseFormResponse;
    }

    @Override
    public List<ReleaseFormResponse> toDtoS(List<ReleaseForm> releaseForms) {
        List<ReleaseFormResponse> releaseFormResponses = new ArrayList<>();
        for (ReleaseForm releaseForm : releaseForms) {
            releaseFormResponses.add(toDto(releaseForm));
        }
        return releaseFormResponses;
    }

    @Override
    public ReleaseForm toDtoReleaseForm(ReleaseForm releaseForm, ReleaseFormRequest request) {
        releaseForm.setName(request.getName());
        return releaseForm;
    }
}
