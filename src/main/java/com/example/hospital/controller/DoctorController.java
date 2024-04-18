package com.example.hospital.controller;

import com.example.hospital.Service.DoctorService;
import com.example.hospital.model.Doctor;
import com.example.hospital.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctor")
@AllArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    @GetMapping("/add")
    public String showAddDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "add-doctor";
    }

    @PostMapping("/add")
    public String addDoctor(@ModelAttribute("doctor") Doctor doctor) {
        doctorService.addDoctor(doctor);
        return "redirect:/doctor/add";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctorById(id);
        return "redirect:/doctor/add";
    }


}
