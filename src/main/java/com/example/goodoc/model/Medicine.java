package com.example.goodoc.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToOne
    @JoinColumn(referencedColumnName = "name")
    private ReleaseForm releaseForm;
    @ManyToOne
    @JoinColumn(referencedColumnName = "name")
    private Reception reception;
    private LocalDate nextDose;
    private LocalTime time;
    private String additionally;
}
