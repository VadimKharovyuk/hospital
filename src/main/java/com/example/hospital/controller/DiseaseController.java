package com.example.hospital.controller;

import com.example.hospital.Service.DiseaseService;
import com.example.hospital.model.Disease;
import com.example.hospital.model.Patient;
import com.example.hospital.repository.DiseaseRepository;
import com.example.hospital.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@AllArgsConstructor
public class DiseaseController {

    private final DiseaseRepository diseaseRepository;
    private final PatientRepository patientRepository;
    private final DiseaseService diseaseService;

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

        // Преобразование строковых значений startDate и endDate в LocalDate
        LocalDate parsedStartDate = LocalDate.parse(startDate);
        LocalDate parsedEndDate = LocalDate.parse(endDate);

        // Установка параметров болезни
        disease.setName(name);
        disease.setStartDate(parsedStartDate);
        disease.setEndDate(parsedEndDate);

        // Привязываем болезнь к пациенту
        disease.setPatient(patient);

        // Сохраняем болезнь в базе данных
        diseaseRepository.save(disease);

        // Перенаправляем пользователя на страницу со списком болезней
        return "redirect:/diseaseslist";
    }


    @GetMapping("/diseaseslist")
    public String getAllDiseases(Model model) {
        model.addAttribute("diseases", diseaseRepository.findAll());
        return "diseaseList";
    }

    @RequestMapping("/deletedisease/{id}")
    public String deleteBiId(@PathVariable Long id) {
        diseaseRepository.deleteById(id);
        return "redirect:/diseaseslist";
    }


    @GetMapping("/statistics")
    public String showStatistics(Model model) {
        int totalDiseases = diseaseService.getTotalDiseases();
        int activeDiseases = diseaseService.getActiveDiseases();
        // Другие расчеты статистики заболеваемости

        model.addAttribute("totalDiseases", totalDiseases);
        model.addAttribute("activeDiseases", activeDiseases);
        // Другие атрибуты для передачи результатов расчетов в шаблон

        return "statistics"; // Имя вашего шаблона для отображения статистики
    }


}













