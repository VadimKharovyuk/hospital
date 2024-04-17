package com.example.hospital.controller;

import com.example.hospital.model.Client;
import com.example.hospital.model.Disease;
import com.example.hospital.model.Doctor;
import com.example.hospital.repository.ClientRepository;
import com.example.hospital.repository.DiseaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class DiseasesController {
    private final DiseaseRepository diseaseRepository;
    private final ClientRepository clientRepository;


    @GetMapping("/addDisease")
    public String showAddDiseaseForm(Model model) {
        // Получение списка всех клиентов из базы данных
        Iterable<Client> clients = clientRepository.findAll();

        // Передача списка клиентов в модель для отображения на странице
        model.addAttribute("clients", clients);

        // Передача пустого объекта Disease для заполнения формы
        model.addAttribute("disease", new Disease());

        return "diseases"; // Возвращает имя представления add_disease.html
    }


    @PostMapping("/addDisease")
    public String addDisease(@ModelAttribute("disease") Disease disease, @RequestParam String clientName) {
        // Поиск клиента по имени
        Optional<Client> optionalClient = clientRepository.findByName(clientName);
        Client client = optionalClient.orElseGet(() -> {
            // Если клиент не найден, создаем нового
            Client newClient = new Client();
            newClient.setName(clientName);
            return clientRepository.save(newClient);
        });

        // Привязываем болезнь к клиенту
        disease.setClient(client);

        // Сохраняем болезнь в базе данных
        diseaseRepository.save(disease);

        return "redirect:/patients"; // перенаправление на страницу со списком пациентов
    }



}
