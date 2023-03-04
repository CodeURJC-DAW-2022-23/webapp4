package com.idealtrip.idealTrip.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.idealtrip.idealTrip.model.Review;
import com.idealtrip.idealTrip.repository.ReviewRepository;
import com.idealtrip.idealTrip.service.ReviewService;

import java.util.List;


@Controller
public class ProfileController {
  
  @Autowired
  private ReviewRepository myRepository;

  @GetMapping("/profile") //Añadir /{id} cuando este disponible la función de iniciar sesión
  public String showProfile(Model model) { //Añadir @PathVariable Long id con la id de arriba
    //Cambiar por findAllById cuando este disponible la función de iniciar sesión
    List<Review> reviews = myRepository.findAll();
    model.addAttribute("review", reviews);
    return "profile";
  }
}
