package com.example.goodoc.service.impl;

import com.example.goodoc.dto.releaseForm.ReleaseFormRequest;
import com.example.goodoc.dto.releaseForm.ReleaseFormResponse;
import com.example.goodoc.exception.CustomException;
import com.example.goodoc.mapper.ReleaseFormMapper;
import com.example.goodoc.model.ReleaseForm;
import com.example.goodoc.repository.ReleaseFormRepository;
import com.example.goodoc.service.ReleaseFormService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReleaseFormServiceImpl implements ReleaseFormService {
    private final ReleaseFormRepository releaseFormRepository;
    private final ReleaseFormMapper releaseFormMapper;

    @Override
    public List<ReleaseFormResponse> all() {
        return releaseFormMapper.toDtoS(releaseFormRepository.findAll());
    }

    @Override
    public ReleaseFormResponse findById(Long id) {
        return releaseFormMapper.toDto(releaseFormRepository.findById(id).orElseThrow(() -> new CustomException("Release form is not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public void updateById(Long id, ReleaseFormRequest request) {
        ReleaseForm releaseForm = releaseFormRepository.findById(id).orElseThrow(() -> new CustomException("Release form is not found", HttpStatus.NOT_FOUND));
        if (releaseFormRepository.findByName(request.getName()).isEmpty()) {
            releaseFormRepository.save(releaseFormMapper.toDtoReleaseForm(releaseForm, request));
        } else {
            throw new CustomException("Release form with this name is already exist", HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public void deleteById(Long id) {
        releaseFormRepository.findById(id).orElseThrow(() -> new CustomException("Release form is not found", HttpStatus.NOT_FOUND));
        releaseFormRepository.deleteById(id);
    }

    @Override
    public void create(ReleaseFormRequest request) {
        if (releaseFormRepository.findByName(request.getName()).isEmpty()) {
            releaseFormRepository.save(releaseFormMapper.toDtoReleaseForm(new ReleaseForm(), request));
        } else {
            throw new CustomException("Release with this name is already exist", HttpStatus.BAD_REQUEST);
        }
    }
}
