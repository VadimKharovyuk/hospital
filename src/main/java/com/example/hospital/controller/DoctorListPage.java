package com.example.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoctorListPage {
    @GetMapping("/doctors")
    public String home(){
        return "doctorList";
    }

}
