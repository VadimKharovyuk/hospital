package com.example.hospital.repository;

import com.example.hospital.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease,Long> {
    List<Disease> findByEndDateIsNull();

    List<Disease> findByEndDateBetween(LocalDate startDate, LocalDate endDate);

}
