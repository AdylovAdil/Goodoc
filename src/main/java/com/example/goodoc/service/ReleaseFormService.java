package com.example.goodoc.service;

import com.example.goodoc.dto.releaseForm.ReleaseFormRequest;
import com.example.goodoc.dto.releaseForm.ReleaseFormResponse;

import java.util.List;

public interface ReleaseFormService {
    List<ReleaseFormResponse> all();

    ReleaseFormResponse findById(Long id);

    void updateById(Long id, ReleaseFormRequest request);

    void deleteById(Long id);

    void create(ReleaseFormRequest request);
}
