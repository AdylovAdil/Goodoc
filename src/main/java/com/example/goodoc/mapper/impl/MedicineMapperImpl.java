package com.example.goodoc.mapper.impl;

import com.example.goodoc.dto.medicine.MedicineRequest;
import com.example.goodoc.dto.medicine.MedicineResponse;
import com.example.goodoc.exception.CustomException;
import com.example.goodoc.mapper.MedicineMapper;
import com.example.goodoc.model.Medicine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class MedicineMapperImpl implements MedicineMapper {
    @Override
    public MedicineResponse toDto(Medicine medicine) {
        MedicineResponse medicineResponse = new MedicineResponse();
        medicineResponse.setName(medicine.getName());
        medicineResponse.setReleaseForm(medicine.getReleaseForm().getName());
        medicineResponse.setReception(medicine.getReception().getName());
        medicineResponse.setNextDose(medicine.getNextDose());
        medicineResponse.setTime(medicine.getTime());
        medicineResponse.setAdditionally(medicine.getAdditionally());
        return medicineResponse;
    }

    @Override
    public List<MedicineResponse> toDtoS(List<Medicine> medicineList) {
        List<MedicineResponse> medicineResponses = new ArrayList<>();
        for (Medicine medicine : medicineList) {
            medicineResponses.add(toDto(medicine));
        }
        return medicineResponses;
    }

    @Override
    public Medicine toDtoMedicine(Medicine medicine, MedicineRequest medicineRequest) {
        medicine.setName(medicineRequest.getName());
        int hour24 = medicineRequest.getHour();
        try {
            Month month = Month.valueOf(medicineRequest.getMonth().toUpperCase());
            medicine.setNextDose(LocalDate.of(medicineRequest.getYear(), month.getValue(), medicineRequest.getDay()));

            if ("PM".equals(medicineRequest.getTimePeriod()) && medicineRequest.getHour() != 12) {
                hour24 += 12;
            } else if ("AM".equals(medicineRequest.getTimePeriod()) && medicineRequest.getHour() == 12) {
                hour24 = 0;
            }
        } catch (Exception e) {
            log.debug("Time format exception");
            throw new CustomException("Error during formatting time", HttpStatus.BAD_GATEWAY);
        }
        medicine.setTime(LocalTime.of(hour24, medicineRequest.getMinute()));
        medicine.setAdditionally(medicineRequest.getAdditionally());
        return medicine;
    }
}
