package com.example.hospital.Service;

import com.example.hospital.model.Client;
import com.example.hospital.model.Patient;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener {


    @RabbitListener(queues = "hospital_fanout_queue")
    public void receiveMessage(Patient patient) {
        // Обработка сообщения
        System.out.println("1 Received  message: " + patient.getName());
    }
    @RabbitListener(queues = "hospital_fanout_queue")
    public void receiveMessage1(Patient patient) {
        // Обработка сообщения
        System.out.println(" 2 Received message: " + patient.getName());
    }


    @RabbitListener(queues = "hospital_fanout_queue")
    public void receiveMessage(Client client) {
        System.out.println("3  слушатель Fanout_ hospital_fanout_queue  " + client.getName());

    }
    @RabbitListener(queues = "hospital_fanout_queue")
    public void receiveMessage1(Client client) {
        System.out.println("4 слушатель Fanout_: _ hospital_queue  " + client.getName());

    }


}


