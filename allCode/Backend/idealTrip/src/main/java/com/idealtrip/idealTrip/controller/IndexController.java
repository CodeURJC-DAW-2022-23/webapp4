package com.idealtrip.idealTrip.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
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
    
    List<Destination> destinationList = destinationService.findAll();
    List<Double> totalRatingList = new ArrayList<>();
    for (Destination destination : destinationList) {
      double totalRating = reviewService.getTotalRatingForDestination(destination);
      totalRatingList.add(totalRating);
    }
    var totalParis= totalRatingList.get(0);
    var totalAlpesJulianos = totalRatingList.get(1);
    var totalAtenas = totalRatingList.get(2);
    var totalBangkok = totalRatingList.get(3);
    var totalLondres = totalRatingList.get(4);
    var totalMaldivas = totalRatingList.get(5);
    var totalSantaMarta = totalRatingList.get(6);
    var totalSingapur = totalRatingList.get(7);
    model.addAttribute("totalRatingList", totalRatingList);
    model.addAttribute("totalParis", totalParis);
    model.addAttribute("totalAlpesJulianos", totalAlpesJulianos);
    model.addAttribute("totalAtenas", totalAtenas);
    model.addAttribute("totalBangkok", totalBangkok);
    model.addAttribute("totalLondres", totalLondres);
    model.addAttribute("totalMaldivas", totalMaldivas);
    model.addAttribute("totalSantaMarta", totalSantaMarta);
    model.addAttribute("totalSingapur", totalSingapur);

    return "rating";
  }
}
