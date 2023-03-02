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

  @GetMapping("/PARIS/informationParis")
  public String servicesParisInfo(Model model) {
    return "PARIS/informationParis";
  }

  @GetMapping("/PARIS/cateringParis")
  public String servicesParisCatering(Model model) {
    return "PARIS/cateringParis";
  }

  @GetMapping("/PARIS/tourismParis")
  public String servicesParistourism(Model model) {
    return "PARIS/tourismParis";
  }

  @GetMapping("/PARIS/reviewParis")
  public String servicesParisreview(Model model) {
    return "PARIS/reviewParis";
  }

  @GetMapping("/PARIS/paris1")
  public String servicesParisHouse(Model model) {
    return "PARIS/paris1";
  }

  

  //Bangkok
  @GetMapping("/services/Bangkok")
  public String servicesBangkok(Model model) {
    return "BANGKOK/informationBangkok";
  }

  @GetMapping("/BANGKOK/informationBangkok")
  public String servicesBangkokInfo(Model model) {
    return "BANGKOK/informationBangkok";
  }

  @GetMapping("/BANGKOK/cateringBangkok")
  public String servicesBangkokCatering(Model model) {
    return "BANGKOK/cateringBangkok";
  }

  @GetMapping("/BANGKOK/tourismBangkok")
  public String servicesBangkoktourism(Model model) {
    return "BANGKOK/tourismBangkok";
  }

  @GetMapping("/BANGKOK/reviewBangkok")
  public String servicesBangkokreview(Model model) {
    return "BANGKOK/reviewBangkok";
  }

  @GetMapping("/BANGKOK/bangkok1")
  public String servicesBangkokHouse(Model model) {
    return "BANGKOK/bangkok1";
  }



  // Maldivas
  @GetMapping("/services/Maldivas")
  public String servicesMaldivas(Model model) {
    return "MALDIVAS/informationMaldivas";
  }

  @GetMapping("/MALDIVAS/informationMaldivas")
  public String servicesMaldivasInfo(Model model) {
    return "MALDIVAS/informationMaldivas";
  }

  @GetMapping("/MALDIVAS/cateringMaldivas")
  public String servicesMaldivasCatering(Model model) {
    return "MALDIVAS/cateringMaldivas";
  }

  @GetMapping("/MALDIVAS/tourismMaldivas")
  public String servicesMaldivastourism(Model model) {
    return "MALDIVAS/tourismMaldivas";
  }

  @GetMapping("/MALDIVAS/reviewMaldivas")
  public String servicesMaldivasreview(Model model) {
    return "MALDIVAS/reviewMaldivas";
  }

  @GetMapping("/MALDIVAS/Maldivas1")
  public String servicesMaldivasHouse(Model model) {
    return "MALDIVAS/Maldivas1";
  }


}