package com.example.goodoc.repository;

import com.example.goodoc.model.ReleaseForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReleaseFormRepository extends JpaRepository<ReleaseForm, Long> {
    Optional<ReleaseForm> findByName(String name);
}
