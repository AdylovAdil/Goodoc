package com.example.goodoc.service.impl;

import com.example.goodoc.dto.reception.ReceptionRequest;
import com.example.goodoc.dto.reception.ReceptionResponse;
import com.example.goodoc.exception.CustomException;
import com.example.goodoc.mapper.ReceptionMapper;
import com.example.goodoc.model.Reception;
import com.example.goodoc.repository.ReceptionRepository;
import com.example.goodoc.service.ReceptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReceptionServiceImpl implements ReceptionService {
    private final ReceptionRepository receptionRepository;
    private final ReceptionMapper receptionMapper;

    @Override
    public List<ReceptionResponse> all() {
        return receptionMapper.toDtoS(receptionRepository.findAll());
    }

    @Override
    public ReceptionResponse findById(Long id) {
        return receptionMapper.toDto(receptionRepository.findById(id).orElseThrow(() -> new CustomException("Reception is not found!", HttpStatus.NOT_FOUND)));
    }

    @Override
    public void updateById(Long id, ReceptionRequest receptionRequest) {
        Reception reception = receptionRepository.findById(id).orElseThrow(() -> new CustomException("Reception is not found!", HttpStatus.NOT_FOUND));
        if (receptionRepository.findByName(receptionRequest.getName()).isEmpty()) {
            receptionRepository.save(receptionMapper.toDtoReception(reception, receptionRequest));
        } else {
            throw new CustomException("Reception with this name is already exist", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void deleteById(Long id) {
        receptionRepository.findById(id).orElseThrow(() -> new CustomException("Reception is not found!", HttpStatus.NOT_FOUND));
        receptionRepository.deleteById(id);
    }

    @Override
    public void create(ReceptionRequest request) {
        if (receptionRepository.findByName(request.getName()).isEmpty()) {
            receptionRepository.save(receptionMapper.toDtoReception(new Reception(), request));
        } else {
            throw new CustomException("Reception with this name is already exist", HttpStatus.BAD_REQUEST);
        }
    }
}
