package com.example.hospital.controller;
import com.example.hospital.Service.EmailService;
import com.example.hospital.model.Client;
import com.example.hospital.model.Doctor;
import com.example.hospital.repository.ClientRepository;
import com.example.hospital.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@AllArgsConstructor
public class RegistrationController {

    private final ClientRepository clientRepository;
    private final EmailService emailService;
    private final DoctorRepository doctorRepository;

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String date,
            @RequestParam Long doctorId) {

        // Создание нового объекта клиента
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

        // Получаем выбранного врача из базы данных
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor Id: " + doctorId));

        // Привязываем клиента к выбранному врачу
        client.setDoctor(doctor);

        // Сохраняем клиента в базе данных
        clientRepository.save(client);

        // Отправка письма подтверждения на электронную почту
        String subject = "Запись на прием в Kharovyuk Vadim clinic к " + doctor.getName();

        // Получаем выбранную дату и время записи клиента
        Date appointmentDate = client.getAppointmentDate();

        // Форматируем дату в нужный формат для сообщения
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String formattedDate = dateFormatter.format(appointmentDate);

        // Обновляем сообщение с информацией о дате записи
        String message = client.getName() + ", вы успешно записались на прием к " + doctor.getName() + ".\n" +
                "Дата и время приема: " + formattedDate + "\n" +
                "Специальность: " + doctor.getSpecialization() + "\n" +
                "Расписание: " + doctor.getSchedule();
        emailService.sendSimpleMessage(email, subject, message);

        return "Запись на прием успешно создана. Письмо отправлено на " + email;
    }
}
