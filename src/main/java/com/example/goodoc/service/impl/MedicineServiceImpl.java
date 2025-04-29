package com.example.goodoc.service.impl;

import com.example.goodoc.dto.medicine.MedicineRequest;
import com.example.goodoc.dto.medicine.MedicineResponse;
import com.example.goodoc.exception.CustomException;
import com.example.goodoc.mapper.MedicineMapper;
import com.example.goodoc.model.Medicine;
import com.example.goodoc.model.Reception;
import com.example.goodoc.model.ReleaseForm;
import com.example.goodoc.repository.MedicineRepository;
import com.example.goodoc.repository.ReceptionRepository;
import com.example.goodoc.repository.ReleaseFormRepository;
import com.example.goodoc.service.MedicineService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;
    private final MedicineMapper medicineMapper;
    private final ReceptionRepository receptionRepository;
    private final ReleaseFormRepository releaseFormRepository;

    @Override
    public List<MedicineResponse> all() {
        return medicineMapper.toDtoS(medicineRepository.findAll());
    }

    @Override
    public MedicineResponse findById(Long id) {
        return medicineMapper.toDto(medicineRepository.findById(id).orElseThrow(() -> new CustomException("Medicine not found!", HttpStatus.NOT_FOUND)));
    }

    @Override
    public void updateById(Long id, MedicineRequest medicineRequest) {
        Medicine medicine = medicineRepository.findById(id).orElseThrow(() -> new CustomException("Medicine not found!", HttpStatus.NOT_FOUND));
        medicineRepository.save(medicineMapper.toDtoMedicine(medicine, medicineRequest));
    }

    @Override
    public void deleteById(Long id) {
        medicineRepository.findById(id).orElseThrow(() -> new CustomException("Medicine not found!", HttpStatus.NOT_FOUND));
        medicineRepository.deleteById(id);
    }

    @Override
    public void addMedicine(MedicineRequest medicineRequest) {
        Reception reception = receptionRepository.findByName(medicineRequest.getReception()).orElseThrow(() -> new CustomException("Reception is not found!", HttpStatus.NOT_FOUND));
        ReleaseForm releaseForm = releaseFormRepository.findByName(medicineRequest.getReleaseForm()).orElseThrow(() -> new CustomException("Release form is not found!", HttpStatus.NOT_FOUND));
        Medicine medicine = new Medicine();
        medicine.setReception(reception);
        medicine.setReleaseForm(releaseForm);
        medicineRepository.save(medicineMapper.toDtoMedicine(medicine, medicineRequest));
    }
}
