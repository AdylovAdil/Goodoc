package com.example.goodoc.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "release-form")
public class ReleaseForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "releaseForm")
    private List<Medicine> medicines;
}
