package com.example.hospital.controller;

import com.example.hospital.model.Doctor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomePage {
    @GetMapping("/")
    public String home(){
        return "home";
    }

}
