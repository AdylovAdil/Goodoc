package com.example.goodoc.repository;

import com.example.goodoc.model.Reception;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceptionRepository extends JpaRepository<Reception, Long> {
    Optional<Reception> findByName(String name);
}
