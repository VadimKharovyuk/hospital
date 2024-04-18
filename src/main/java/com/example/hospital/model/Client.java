package com.example.hospital.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Date appointmentDate;
    private String email;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "doctor_id") // Название столбца в базе данных, который содержит идентификатор доктора
    private Doctor doctor;



}
