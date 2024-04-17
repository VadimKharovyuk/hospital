package com.example.hospital.controller;

import com.example.hospital.model.Client;
import com.example.hospital.repository.ClientRepository;
import com.example.hospital.repository.DiseaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class Patients {
    private final ClientRepository clientRepository;
    @GetMapping("/patients")
    public String patients(Model model) {
        // Получение списка всех клиентов из базы данных
        Iterable<Client> clients = clientRepository.findAll();

        // Передача списка клиентов в модель для отображения на странице
        model.addAttribute("clients", clients);

        // Возврат имени представления, которое отобразит список клиентов
        return "patients";
    }
}