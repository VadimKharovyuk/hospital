package com.example.hospital.controller;

import com.example.hospital.model.Disease;
import com.example.hospital.model.Patient;
import com.example.hospital.repository.DiseaseRepository;
import com.example.hospital.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class DiseaseController {

    private final DiseaseRepository diseaseRepository;
    private final PatientRepository patientRepository;

    @GetMapping("/disease")
    public String showAddDiseaseForm(Model model) {
        // Получение списка всех пациентов из базы данных
        Iterable<Patient> patients = patientRepository.findAll();

        // Передача списка пациентов в модель для отображения на странице
        model.addAttribute("patients", patients);

        // Передача пустого объекта Disease для заполнения формы
        model.addAttribute("disease", new Disease());

        return "diseasesPage"; // Возвращает имя представления add_disease.html
    }




    @PostMapping("/addDisease")
    public String addDisease(
            @RequestParam Long patientId,
            @ModelAttribute("disease") Disease disease,
            @RequestParam String name,
            @RequestParam String startDate,
            @RequestParam String endDate) {

        // Получение пациента из базы данных по его идентификатору
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // Установка параметров болезни
        disease.setName(name);
        disease.setStartDate(startDate);
        disease.setEndDate(endDate);

        // Привязываем болезнь к пациенту
        disease.setPatient(patient);

        // Сохраняем болезнь в базе данных
        diseaseRepository.save(disease);

        // Перенаправляем пользователя на страницу со списком пациентов
        return "home";
    }



}













