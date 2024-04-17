package com.example.hospital.model;

import com.example.hospital.model.Disease;
import com.example.hospital.model.Medication;
import com.example.hospital.model.Procedure;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String gender;
    private String contact;

    @OneToMany(mappedBy = "patient")
    private List<Disease> diseases;

    @OneToMany(mappedBy = "patient")
    private List<Medication> medications;

    @OneToMany(mappedBy = "patient")
    private List<Procedure> procedures;


}
