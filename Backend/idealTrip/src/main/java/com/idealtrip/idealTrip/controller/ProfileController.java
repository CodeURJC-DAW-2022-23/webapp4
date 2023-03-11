package com.idealtrip.idealTrip.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

//import com.idealtrip.idealTrip.model.Review;
import com.idealtrip.idealTrip.model.User;
//import com.idealtrip.idealTrip.repository.ReviewRepository;
//import com.idealtrip.idealTrip.service.ReviewService;
import com.idealtrip.idealTrip.service.UserService;

import java.security.Principal;



@Controller
public class ProfileController {
  
  

  @Autowired
  private UserService users;

	//@Autowired
	//private ReviewService myRepository;

  User currentUser;
	//Pageable pageable = PageRequest.of(0, 5);
	//Page<Review> review = myRepository.findReviewByUser(currentUser, pageable);

  @ModelAttribute
	public void addAttribute(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			users.findByEmail(principal.getName()).ifPresent(us -> currentUser = us);
			model.addAttribute("logged", true);
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("email", currentUser.getEmail());
      //model.addAttribute("review", review);
			model.addAttribute("review", currentUser.getReviews());
			model.addAttribute("imageProfileFile", currentUser.getProfileAvatarFile());
			model.addAttribute("imageProfile", currentUser.getProfileAvatar());

			model.addAttribute("admin", request.isUserInRole("ADMIN"));
		} else {
			model.addAttribute("logged", false);
		}
	}

  @GetMapping("/profile") 
    public String showProfile(Model model ) { 
    
    return "profile";
  }
}
