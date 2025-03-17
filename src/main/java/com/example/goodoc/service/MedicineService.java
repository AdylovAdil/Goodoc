package com.example.goodoc.service;

import com.example.goodoc.dto.medicine.MedicineRequest;
import com.example.goodoc.dto.medicine.MedicineResponse;

import java.util.List;

public interface MedicineService {
    List<MedicineResponse> all();

    MedicineResponse findById(Long id);

    void updateById(Long id, MedicineRequest medicineRequest);

    void deleteById(Long id);

    void addMedicine(MedicineRequest medicineRequest);
}
