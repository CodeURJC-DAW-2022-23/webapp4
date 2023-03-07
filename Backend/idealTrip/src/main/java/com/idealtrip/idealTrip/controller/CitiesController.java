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
import com.idealtrip.idealTrip.service.UserService;

@Controller
public class CitiesController {
  @Autowired
  private CateringService cateringService;

  @Autowired
  private UserService userService;

  @Autowired
  private DestinationService destinationService;

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

  // Paris
  // @GetMapping("/services/Paris")
  // public String servicesParis(Model model) {
  // return "PARIS/informationParis";
  // }

  // @GetMapping("/PARIS/informationParis")
  // public String servicesParisInfo(Model model) {
  // return "PARIS/informationParis";
  // }

  @GetMapping("/catering/{id}")
  public String servicesParisCatering(Model model, @PathVariable Long id) {

    model.addAttribute("name", destinationService.findById(id).get().getNameDestination());
    model.addAttribute("nameDestination", cateringService.findByDestination(id));
    // model.addAttribute("catering", cateringService.findAll());
    return "catering";
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

  // Bangkok
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

  // Atenas
  @GetMapping("/services/Atenas")
  public String servicesAtenas(Model model) {
    return "ATENAS/informationAtenas";
  }

  @GetMapping("/ATENAS/informationAtenas")
  public String servicesAtenasInfo(Model model) {
    return "ATENAS/informationAtenas";
  }

  @GetMapping("/ATENAS/cateringAtenas")
  public String servicesAtenasCatering(Model model) {
    return "ATENAS/cateringAtenas";
  }

  @GetMapping("/ATENAS/tourismAtenas")
  public String servicesAtenastourism(Model model) {
    return "ATENAS/tourismAtenas";
  }

  @GetMapping("/ATENAS/reviewAtenas")
  public String servicesAtenasreview(Model model) {
    return "ATENAS/reviewAtenas";
  }

  @GetMapping("/ATENAS/atenas1")
  public String servicesAtenasHouse(Model model) {
    return "ATENAS/atenas1";
  }

  // Londres
  @GetMapping("/services/Londres")
  public String servicesLondres(Model model) {
    return "Londres/informationLondres";
  }

  @GetMapping("/LONDRES/informationLondres")
  public String servicesLondresInfo(Model model) {
    return "Londres/informationLondres";
  }

  @GetMapping("/LONDRES/cateringLondres")
  public String servicesLondresCatering(Model model) {
    return "Londres/cateringLondres";
  }

  @GetMapping("/LONDRES/tourismLondres")
  public String servicesLondrestourism(Model model) {
    return "Londres/tourismLondres";
  }

  @GetMapping("/LONDRES/reviewLondres")
  public String servicesLondresreview(Model model) {
    return "Londres/reviewLondres";
  }

  @GetMapping("/LONDRES/londres1")
  public String servicesLondresHouse(Model model) {
    return "Londres/londres1";
  }

  // Alpes_Julianos
  @GetMapping("/services/AlpesJulianos")
  public String servicesAlpesJulianos(Model model) {
    return "ALPESJULIANOS/informationAlpesJulianos";
  }

  @GetMapping("/ALPESJULIANOS/informationAlpesJulianos")
  public String servicesAlpesJulianosInfo(Model model) {
    return "ALPESJULIANOS/informationAlpesJulianos";
  }

  @GetMapping("/ALPESJULIANOS/cateringAlpesJulianos")
  public String servicesAlpesJulianosCatering(Model model) {
    return "ALPESJULIANOS/cateringAlpesJulianos";
  }

  @GetMapping("/ALPESJULIANOS/tourismAlpesJulianos")
  public String servicesAlpesJulianostourism(Model model) {
    return "ALPESJULIANOS/tourismAlpesJulianos";
  }

  @GetMapping("/ALPESJULIANOS/reviewAlpesJulianos")
  public String servicesAlpesJulianosreview(Model model) {
    return "ALPESJULIANOS/reviewAlpesJulianos";
  }

  @GetMapping("/ALPESJULIANOS/alpesjulianos1")
  public String servicesAlpesJulianosHouse(Model model) {
    return "ALPESJULIANOS/alpesjulianos1";
  }

  // Santa_Marta
  @GetMapping("/services/SantaMarta")
  public String servicesSantaMarta(Model model) {
    return "SANTAMARTA/informationSantaMarta";
  }

  @GetMapping("/SANTAMARTA/informationSantaMarta")
  public String servicesSantaMartaInfo(Model model) {
    return "SANTAMARTA/informationSantaMarta";
  }

  @GetMapping("/SANTAMARTA/cateringSantaMarta")
  public String servicesSantaMartaCatering(Model model) {
    return "SANTAMARTA/cateringSantaMarta";
  }

  @GetMapping("/SANTAMARTA/tourismSantaMarta")
  public String servicesSantaMartatourism(Model model) {
    return "SANTAMARTA/tourismSantaMarta";
  }

  @GetMapping("/SANTAMARTA/reviewSantaMarta")
  public String servicesSantaMartareview(Model model) {
    return "SANTAMARTA/reviewSantaMarta";
  }

  @GetMapping("/SANTAMARTA/santamarta1")
  public String servicesSantaMartaHouse(Model model) {
    return "SANTAMARTA/santamarta1";
  }

  // Singapur
  @GetMapping("/services/Singapur")
  public String servicesSingapur(Model model) {
    return "SINGAPUR/informationSingapur";
  }

  @GetMapping("/SINGAPUR/informationSingapur")
  public String servicesSingapurInfo(Model model) {
    return "SINGAPUR/informationSingapur";
  }

  @GetMapping("/SINGAPUR/cateringSingapur")
  public String servicesSingapurCatering(Model model) {
    return "SINGAPUR/cateringSingapur";
  }

  @GetMapping("/SINGAPUR/tourismSingapur")
  public String servicesSingapurtourism(Model model) {
    return "SINGAPUR/tourismSingapur";
  }

  @GetMapping("/SINGAPUR/reviewSingapur")
  public String servicesSingapurreview(Model model) {
    return "SINGAPUR/reviewSingapur";
  }

  @GetMapping("/SINGAPUR/singapur")
  public String servicesSingapurHouse(Model model) {
    return "SINGAPUR/singapur";
  }

}