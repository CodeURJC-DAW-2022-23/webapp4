package com.idealtrip.idealTrip.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
// import java.net.http.HttpHeaders;
import java.security.Principal;
import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.core.io.InputStreamResource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.model.Review;
// import com.fasterxml.jackson.annotation.JsonCreator.Mode;
// import com.idealtrip.idealTrip.model.Catering;
import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.model.Review;
import com.idealtrip.idealTrip.model.Tourism;
import com.idealtrip.idealTrip.model.User;
import com.idealtrip.idealTrip.service.CateringService;
import com.idealtrip.idealTrip.service.DestinationService;
import com.idealtrip.idealTrip.service.HouseService;
import com.idealtrip.idealTrip.service.ReviewService;
import com.idealtrip.idealTrip.service.TourismService;
import com.idealtrip.idealTrip.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;


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

  @Autowired
  private ReviewService reviewService;
  
  @Autowired
  private HouseService houseService;

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

  @GetMapping("/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable Long id) throws SQLException {

		Optional<Tourism> tourism = tourismService.findById(id);
		if (tourism.isPresent() && tourism.get().getImageTourismURL() != null) {

			Resource file = (Resource) new InputStreamResource(tourism.get().getImageTourismFile().getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpg")
					.contentLength(tourism.get().getImageTourismFile().length()).body(file);

		} else {
			return ResponseEntity.notFound().build();
		}
	}



  // @GetMapping("/information8")
  // public String parisSearch(){
  //   return "/8/information8";
  // }

  
  @PostMapping("/review/{id}")
  public String addReview(Model model,
                         @PathVariable Long id, 
                         @RequestParam String titleReview,
                         @RequestParam int ratingReview ,
                         @RequestParam String contentReview) {
    
    Destination currentDestination = destinationService.findDestinationById(id).orElse(null);
    Review review = new Review(id, currentUser, currentDestination, titleReview, ratingReview,
                              contentReview);
    reviewService.save(review);
    //model.addAttribute("userReviews", review);
    long destinationId = destinationService.findById(id).get().getId();        
    return "redirect:/review/" + destinationId;
}

@GetMapping("/review/{id}")
  public String reviewByDestinationId(@PathVariable Long id, Model model) {
  model.addAttribute("reviews", reviewService.findReviewsByDestinationId(id));
  return "review";
}

  @GetMapping("/house/{id}")
  public String houseByDestinationId(@PathVariable Long id, Model model) {
  model.addAttribute("houses", houseService.findByDestinationName(destinationService.findById(id).get().getNameDestination()));
  return "house";
  }

}