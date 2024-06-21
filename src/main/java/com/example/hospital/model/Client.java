package com.example.hospital.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Игнорируем свойства Hibernate для сериализации
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDateTime appointmentDate;
    private String email;
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY) // Многие к одному со связью LAZY
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
