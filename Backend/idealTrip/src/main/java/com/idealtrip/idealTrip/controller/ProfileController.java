package com.idealtrip.idealTrip.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.idealtrip.idealTrip.model.Review;
import com.idealtrip.idealTrip.model.User;
import com.idealtrip.idealTrip.repository.ReviewRepository;
import com.idealtrip.idealTrip.service.ReviewService;
import com.idealtrip.idealTrip.service.UserService;

import java.security.Principal;
import java.util.List;


@Controller
public class ProfileController {
  
  @Autowired
  private ReviewRepository myRepository;

  @Autowired
  private UserService users;

  User currentUser;

  @ModelAttribute
	public void addAttribute(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			users.findByEmail(principal.getName()).ifPresent(us -> currentUser = us);
			model.addAttribute("logged", true);
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("email", currentUser.getEmail());
      model.addAttribute("review", currentUser.getReviews());

			model.addAttribute("admin", request.isUserInRole("ADMIN"));
		} else {
			model.addAttribute("logged", false);
		}
	}
	@GetMapping("/profile")
	public String showProfile (Model model){
	  if (user.isPresent());

	}

  @GetMapping("/profile") 
    public String showProfile(Model model ) { 
    
    return "profile";
  }
}
