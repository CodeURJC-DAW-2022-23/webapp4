package com.idealtrip.idealTrip.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.idealtrip.idealTrip.model.Catering;
import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.model.User;
import com.idealtrip.idealTrip.service.CateringService;
import com.idealtrip.idealTrip.service.DestinationService;
import com.idealtrip.idealTrip.service.TourismService;
import com.idealtrip.idealTrip.service.UserService;

@Controller
public class CitiesController {
  @Autowired
  private CateringService cateringService;

  @Autowired
  private UserService userService;

  @Autowired
  private DestinationService destinationService;

  @Autowired
  private TourismService tourismService;

  User currentUser;

  @ModelAttribute
  public void addAttributes(Model model, HttpServletRequest request) {
    Principal principal = request.getUserPrincipal();

    if (principal != null) {
      userService.findByEmail(principal.getName()).ifPresent(us -> currentUser = us);
      model.addAttribute("logged", true);
      model.addAttribute("userName", principal.getName());
      model.addAttribute("admin", request.isUserInRole("ADMIN"));
      model.addAttribute("curretUser", currentUser);

    } else {
      model.addAttribute("logged", false);
    }
  }

  @GetMapping("/catering/{id}")
  public String servicesAllCatering(Model model, @PathVariable Long id) {
    model.addAttribute("name", destinationService.findById(id).get().getNameDestination());
    model.addAttribute("nameDestination", cateringService.findByDestination(id));
    // model.addAttribute("catering", cateringService.findAll());
    return "catering";
  }

  @GetMapping("/tourism/{id}")
  public String servicesAllTourism(Model model, @PathVariable Long id) {
    model.addAttribute("name", destinationService.findById(id).get().getNameDestination());
    model.addAttribute("nameDestination", tourismService.findByDestinationId(id));
    // model.addAttribute("catering", cateringService.findAll());
    return "tourism";
  }

  // @GetMapping("/PARIS/tourismParis")
  // public String servicesParistourism(Model model) {my
  // return "PARIS/tourismParis";
  // }

  // @GetMapping("/PARIS/reviewParis")
  // public String servicesParisreview(Model model) {
  // return "PARIS/reviewParis";
  // }

  // @GetMapping("/PARIS/paris1")
  // public String servicesParisHouse(Model model) {
  // return "PARIS/paris1";
  // }
  

 

}