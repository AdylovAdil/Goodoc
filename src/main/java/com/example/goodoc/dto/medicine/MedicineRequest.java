package com.example.goodoc.dto.medicine;

import lombok.Data;

@Data
public class MedicineRequest {
    private String name;
    private String releaseForm;
    private String reception;

    private String month;
    private int day;
    private int year;

    private int hour;
    private int minute;
    private String timePeriod;

    private String additionally;
}
