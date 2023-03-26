package com.idealtrip.idealTrip.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.service.DestinationService;
import com.idealtrip.idealTrip.service.ReviewService;
import com.idealtrip.idealTrip.service.UserService;



@RestController
@RequestMapping("/api/services")
public class IndexRestController {

  @Autowired
  private DestinationService destinationService;


  @GetMapping("/")
  public List<Destination> getServices(){
    return destinationService.findAll();
  }

  
  
}
