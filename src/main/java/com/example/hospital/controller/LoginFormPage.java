package com.example.hospital.controller;

import com.example.hospital.model.Client;
import com.example.hospital.model.Doctor;
import com.example.hospital.repository.ClientRepository;
import com.example.hospital.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class LoginFormPage {
    private final DoctorRepository doctorRepository;
    private final ClientRepository clientRepository;

    @GetMapping("/login")
    public String showAppointmentForm(Model model) {
        List<Doctor> doctors = doctorRepository.findAll(); // Предположим, что doctorRepository - это ваш репозиторий для работы с сущностью Doctor
        model.addAttribute("doctors", doctors);
        return "login"; // Возвращаем имя представления (шаблона HTML)
    }

    @PostMapping("/submit_appointment")
    @ResponseBody
    public String submitAppointment(
            @RequestParam Long doctorId,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String date) {

        // Получаем выбранного врача из базы данных
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor Id: " + doctorId));

        // Создаем нового клиента
        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        client.setPhone(phone);
        // Парсим строку с датой и временем и устанавливаем в объект Date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            Date appointmentDate = formatter.parse(date);
            client.setAppointmentDate(appointmentDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Ошибка при обработке даты и времени";
        }

        // Привязываем клиента к выбранному врачу
        client.setDoctor(doctor);

        // Сохраняем клиента в базе данных
        clientRepository.save(client);

        return "Запись на прием успешно создана!";
    }


}
