package com.example.hospital.controller;

import com.example.hospital.model.Client;
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

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class DiseaseController {

    private final DiseaseRepository diseaseRepository;
    private final PatientRepository patientRepository;

    @GetMapping("/addDisease")
    public String showAddDiseaseForm(Model model) {
        // Получение списка всех пациентов из базы данных
        List<Patient> patients = patientRepository.findAll();

        // Передача списка пациентов в модель для отображения на странице
        model.addAttribute("patients", patients);

        // Передача пустого объекта Disease для заполнения формы
        model.addAttribute("disease", new Disease());

        return "diseases"; // Возвращает имя представления add_disease.html
    }

    @PostMapping("/addDisease")
    public String addDisease(
            @RequestParam Long patientId,
            @ModelAttribute("disease") Disease disease) {
        // Получаем пациента из базы данных по его идентификатору
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            // Устанавливаем найденного пациента в заболевании
            disease.setPatient(patient);

            // Сохраняем заболевание в базе данных
            diseaseRepository.save(disease);

            // Перенаправляем пользователя на страницу со списком пациентов
            return "redirect:/patients";
        } else {
            // В случае, если пациент не найден, можно выполнить соответствующие действия, например, вывести сообщение об ошибке
            return "error";
        }
    }




}








