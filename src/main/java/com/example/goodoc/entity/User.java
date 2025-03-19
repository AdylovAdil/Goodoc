package com.example.goodoc.entity;

import com.example.goodoc.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String surname;
    private String number;

    @Enumerated(EnumType.STRING)
    private Role role;
}
