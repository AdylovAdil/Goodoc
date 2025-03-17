package com.example.goodoc.dto.medicine;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class MedicineResponse {
    private String name;
    private String releaseForm;
    private String reception;
    private LocalDate nextDose;
    private LocalTime time;
    private String additionally;
}
