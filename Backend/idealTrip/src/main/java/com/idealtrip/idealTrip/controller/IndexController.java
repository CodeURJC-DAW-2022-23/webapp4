package com.idealtrip.idealTrip.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.idealtrip.idealTrip.model.User;
import com.idealtrip.idealTrip.service.DestinationService;
import com.idealtrip.idealTrip.service.UserService;

@Controller
public class IndexController {

  @Autowired
  private DestinationService destinationService;

  @Autowired
  private UserService userService;

  User currentUser;


    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();

        if (principal != null){
            userService.findByEmail(principal.getName()).ifPresent(us->currentUser=us);
            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
            model.addAttribute("curretUser", currentUser);
            
        }else{
            model.addAttribute("logged", false);
        }
    }

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
