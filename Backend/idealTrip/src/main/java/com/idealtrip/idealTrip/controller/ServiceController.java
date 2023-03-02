package com.idealtrip.idealTrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServiceController {
    
    @GetMapping("/services")
     public String services(Model model){
         return "services";
     }
}
