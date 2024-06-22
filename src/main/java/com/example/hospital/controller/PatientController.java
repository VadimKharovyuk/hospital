package com.example.hospital.controller;
import com.example.hospital.Service.PatientService;
import com.example.hospital.model.Patient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/patient")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient"; // Возвращает имя представления add_patient.html
    }

    @PostMapping("/addpatients")
    public String addPatient(@ModelAttribute Patient patient) {
        patientService.addPatient(patient);
        return "redirect:/patient"; // Перенаправление на страницу со списком пациентов
    }
}


