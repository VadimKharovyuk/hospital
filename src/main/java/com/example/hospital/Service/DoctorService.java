package com.example.hospital.Service;

import com.example.hospital.model.Doctor;
import com.example.hospital.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    public void addDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }
    public void deleteDoctorById(Long id) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        doctorOptional.ifPresent(doctorRepository::delete);
        System.out.println("hello");
    }




}
