package com.example.goodoc.mapper;

import com.example.goodoc.dto.releaseForm.ReleaseFormRequest;
import com.example.goodoc.dto.releaseForm.ReleaseFormResponse;
import com.example.goodoc.model.ReleaseForm;

import java.util.List;

public interface ReleaseFormMapper {
    ReleaseFormResponse toDto(ReleaseForm releaseForm);

    List<ReleaseFormResponse> toDtoS(List<ReleaseForm> releaseForms);

    ReleaseForm toDtoReleaseForm(ReleaseForm releaseForm, ReleaseFormRequest request);
}
