package com.example.hospital.Service;

import com.example.hospital.model.Disease;
import com.example.hospital.repository.DiseaseRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor

public class DiseaseService {
    private final DiseaseRepository diseaseRepository;


    // Метод для получения общего количества заболеваний
    public int getTotalDiseases() {
        List<Disease> diseases = diseaseRepository.findAll();
        return diseases.size();
    }

    // Метод для получения количества активных заболеваний
    public int getActiveDiseases() {
        List<Disease> activeDiseases = diseaseRepository.findByEndDateIsNull();
        return activeDiseases.size();
    }
}
