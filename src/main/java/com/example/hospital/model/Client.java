package com.example.hospital.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

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
    private Date appointmentDate;
    private String email;
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY) // Многие к одному со связью LAZY
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
