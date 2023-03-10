package com.idealtrip.idealTrip.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.model.User;
import com.idealtrip.idealTrip.service.DestinationService;
import com.idealtrip.idealTrip.service.UserService;
import com.idealtrip.idealTrip.service.ReviewService;

@Controller
public class IndexController {

  @Autowired
  private DestinationService destinationService;

  @Autowired
  private UserService userService;

  @Autowired
  private ReviewService reviewService;

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

  @GetMapping("/index")
  public String SearchCountries(Model model) {
    model.addAttribute("services", destinationService.findAll());
    return "index";
  }

  @GetMapping("/")
  public String searchCountriesInd(Model model) {
    model.addAttribute("services", destinationService.findAll());
    return "index";
  }

  @GetMapping("/rating")

  public String ratingDestination(Model model) {
    List<String> labels = new ArrayList<>();
    List<Integer> datas = new ArrayList<>();
    List<Destination> destinationList = destinationService.findAll();
    Map<String, Double> totalRatingMap = new HashMap<>();
    for (Destination destination : destinationList) {
      int totalRating = reviewService.getTotalRatingForDestination(destination);
      // totalRatingMap.put(destination.getNameDestination(), totalRating);
      labels.add(destination.getNameDestination());
      datas.add(totalRating);
      
    }
    System.out.println(labels);
    System.out.println(datas);
    model.addAttribute("labels", labels); 
    model.addAttribute("datas", datas);
    return "rating";
  }
}
