package com.example.hospital.Service;

import com.example.hospital.model.Client;
import com.example.hospital.model.Doctor;
import com.example.hospital.repository.ClientRepository;
import com.example.hospital.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final ClientRepository clientRepository;
    private final DoctorRepository doctorRepository;
    private final EmailService emailService;

    public String registerClient(String name, String email, String phone, String date, Long doctorId) {
        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        client.setPhone(phone);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            Date appointmentDate = formatter.parse(date);
            client.setAppointmentDate(appointmentDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Ошибка при обработке даты и времени";
        }

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor Id: " + doctorId));

        client.setDoctor(doctor);

        clientRepository.save(client);

        // Отправка письма подтверждения на электронную почту
        String subject = "Запись на прием в Liudmyla Ryzhkova clinic к " + doctor.getName();

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String formattedDate = dateFormatter.format(client.getAppointmentDate());

        String message = client.getName() + ", вы успешно записались на прием к " + doctor.getName() + ".\n" +
                "Дата и время приема: " + formattedDate + "\n" +
                "Специальность: " + doctor.getSpecialization() + "\n" +
                "Расписание: " + doctor.getSchedule();

        emailService.sendSimpleMessage(email, subject, message);

        return "Запись на прием успешно создана. Письмо отправлено на " + email;
    }
}
