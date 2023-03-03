package com.idealtrip.idealTrip.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.service.DestinationService;

@Controller
public class IndexController {

  @Autowired
  private DestinationService destinationService;

  @GetMapping("/index")
  public String index(Model model) {
    return "index";
  }

  // @GetMapping("/index")
  // public String showDestinations(Model model){
  // // model.addAttribute("destinations", destinationService.findAll());
  // return "index";
  // }


}
