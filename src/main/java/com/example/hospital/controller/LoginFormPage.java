package com.example.hospital.controller;

import com.example.hospital.Service.RegistrationService;
import com.example.hospital.model.Client;
import com.example.hospital.model.Doctor;
import com.example.hospital.repository.ClientRepository;
import com.example.hospital.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;



@Controller
@AllArgsConstructor
public class LoginFormPage {
    private final DoctorRepository doctorRepository;
    private final ClientRepository clientRepository;
    private final  RegistrationService registrationService;


    @GetMapping("/login")
    public String showAppointmentForm(Model model) {
        List<Doctor> doctors = doctorRepository.findAll(); // Предположим, что doctorRepository - это ваш репозиторий для работы с сущностью Doctor
        model.addAttribute("doctors", doctors);
        return "login"; // Возвращаем имя представления (шаблона HTML)
    }


    @PostMapping("/submit_appointment")
    public String submitAppointment(
            @RequestParam Long doctorId,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime date
          ) {
        registrationService.registerClient(name,email,phone,doctorId,date);
        return "redirect:/appointments";
    }



    @GetMapping("/appointments")
    public String getAppointments(Model model) {
        // Получение списка записей на прием из базы данных
        List<Client> appointments = clientRepository.findAll();
        // Передача списка записей на прием в представление
        model.addAttribute("appointments", appointments);
        return "listAppointments"; // Возвращает имя представления
    }


    @RequestMapping("/delete/{id}")
    public String deleteBiId(@PathVariable Long id) {
        clientRepository.deleteById(id);
        return "redirect:/appointments";
    }

}
