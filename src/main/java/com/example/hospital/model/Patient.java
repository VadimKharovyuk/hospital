package com.example.hospital.model;

import com.example.hospital.model.Disease;
import com.example.hospital.model.Medication;
import com.example.hospital.model.Procedure;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Patient  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String gender;
    private String contact;

    @OneToMany(mappedBy = "patient")
    @JsonManagedReference
    private List<Disease> diseases;

    @OneToMany(mappedBy = "patient")
    @JsonManagedReference
    private List<Medication> medications;

    @OneToMany(mappedBy = "patient")
    @JsonManagedReference
    private List<Procedure> procedures;


}
