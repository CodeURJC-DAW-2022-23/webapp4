package com.idealtrip.idealTrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CitiesController {

  //Paris 
  @GetMapping("/services/Paris")
  public String servicesParis(Model model) {
    return "PARIS/informationParis";
  }

  @GetMapping("/PARIS/informationParis.html")
  public String servicesParisInfo(Model model) {
    return "PARIS/informationParis";
  }

  @GetMapping("/PARIS/cateringParis.html")
  public String servicesParisCatering(Model model) {
    return "PARIS/cateringParis";
  }

  @GetMapping("/PARIS/tourismParis.html")
  public String servicesParistourism(Model model) {
    return "PARIS/tourismParis";
  }

  @GetMapping("/PARIS/reviewParis.html")
  public String servicesParisreview(Model model) {
    return "PARIS/reviewParis";
  }

  @GetMapping("/PARIS/paris1.html")
  public String servicesParisHouse(Model model) {
    return "PARIS/paris1";
  }

  

  //Bangkok
  @GetMapping("/services/Bangkok")
  public String servicesBangkok(Model model) {
    return "BANGKOK/informationBangkok";
  }

  @GetMapping("/BANGKOK/informationBangkok.html")
  public String servicesBangkokInfo(Model model) {
    return "BANGKOK/informationBangkok";
  }

  @GetMapping("/BANGKOK/cateringBangkok.html")
  public String servicesBangkokCatering(Model model) {
    return "BANGKOK/cateringBangkok";
  }

  @GetMapping("/BANGKOK/tourismBangkok.html")
  public String servicesBangkoktourism(Model model) {
    return "BANGKOK/tourismBangkok";
  }

  @GetMapping("/BANGKOK/reviewBangkok.html")
  public String servicesBangkokreview(Model model) {
    return "BANGKOK/reviewBangkok";
  }

  @GetMapping("/BANGKOK/bangkok1.html")
  public String servicesBangkokHouse(Model model) {
    return "BANGKOK/bangkok1";
  }


}