package com.idealtrip.idealTrip.controller;

// import java.net.http.HttpHeaders;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.core.io.InputStreamResource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.idealtrip.idealTrip.model.Catering;
import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.model.Tourism;
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

  
  

 

}