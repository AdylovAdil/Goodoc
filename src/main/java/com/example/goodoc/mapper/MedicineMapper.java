package com.example.goodoc.mapper;

import com.example.goodoc.dto.medicine.MedicineRequest;
import com.example.goodoc.dto.medicine.MedicineResponse;
import com.example.goodoc.model.Medicine;

import java.util.List;

public interface MedicineMapper {
    MedicineResponse toDto(Medicine medicine);

    List<MedicineResponse> toDtoS(List<Medicine> medicineList);

    Medicine toDtoMedicine(Medicine medicine, MedicineRequest medicineRequest);
}
