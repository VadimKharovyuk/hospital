package com.example.hospital.Service;

import com.example.hospital.config.RabbitMQConfig;
import com.example.hospital.model.Client;
import com.example.hospital.model.Doctor;
import com.example.hospital.repository.ClientRepository;
import com.example.hospital.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final ClientRepository clientRepository;
    private final DoctorRepository doctorRepository;
    private final EmailService emailService;
    private final RabbitTemplate rabbitTemplate;

    public String registerClient(String name, String email, String phone, Long doctorId) {
        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        client.setPhone(phone);

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor Id: " + doctorId));

        client.setDoctor(doctor);

        clientRepository.save(client);

        // Отправка сообщения в RabbitMQ
        rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE_NAME, "", client);

        // Отправка письма подтверждения на электронную почту
        String subject = "Запись на прием в Liudmyla Ryzhkova clinic к " + doctor.getName();
        String message = client.getName() + ", вы успешно записались на прием к " + doctor.getName() + ".\n" +
                "Специальность: " + doctor.getSpecialization() + "\n" +
                "Расписание: " + doctor.getSchedule();

        emailService.sendSimpleMessage(email, subject, message);

        return "Запись на прием успешно создана. Письмо отправлено на " + email;
    }
}
