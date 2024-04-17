package com.example.hospital.controller;

import com.example.hospital.model.Patient;
import com.example.hospital.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class PatientController {
    private final PatientRepository patientRepository;

    @GetMapping("/patient")
    public String showAddPatientForm() {
        return "patient"; // Возвращает имя представления add_patient.html
    }

    @PostMapping("/addpatients")
    public String addPatient(@ModelAttribute Patient patient) {
        // Сохранение пациента в базе данных
        patientRepository.save(patient);
        return "redirect:/patient"; // Перенаправление на страницу со списком пациентов
    }
}

