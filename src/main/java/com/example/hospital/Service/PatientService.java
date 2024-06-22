package com.example.hospital.Service;

import com.example.hospital.config.RabbitMQConfig;
import com.example.hospital.model.Patient;
import com.example.hospital.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final RabbitTemplate rabbitTemplate;


    public void addPatient(Patient patient) {
        // Сохранение пациента в базе данных
        patientRepository.save(patient);

        // Отправка сообщения в очередь Fanout
        rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE_NAME, "", patient);
    }
}
