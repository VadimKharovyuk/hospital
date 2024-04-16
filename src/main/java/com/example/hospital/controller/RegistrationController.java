package com.example.hospital.controller;

import com.example.hospital.Service.EmailService;
import com.example.hospital.model.Client;
import com.example.hospital.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor


public class RegistrationController {
   private final ClientRepository clientRepository;
   private final EmailService emailService;

    @PostMapping("/register")
    public String registerUser(@RequestParam String name, @RequestParam String email, @RequestParam String phone) {
        // Создание нового объекта клиента
        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        client.setPhone(phone);

        // Отправка письма подтверждения на электронную почту
        String subject = "Запись на прием";
        String message = "Вы успешно записались на прием!";
        emailService.sendSimpleMessage(email, subject, message);

        // Предположим, что здесь будет код для сохранения клиента в базу данных
        // Предположим, что здесь будет код для сохранения клиента в базу данных
        clientRepository.save(client);


        return "Запись на прием успешно создана. Письмо отправлено на " + email;
    }

}
