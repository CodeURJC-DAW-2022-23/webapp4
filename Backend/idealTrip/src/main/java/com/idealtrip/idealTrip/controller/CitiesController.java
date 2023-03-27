package com.idealtrip.idealTrip.controller;

import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.idealtrip.idealTrip.model.Catering;
import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.model.House;
import com.idealtrip.idealTrip.model.Review;
import com.idealtrip.idealTrip.model.Tourism;
import com.idealtrip.idealTrip.model.User;
import com.idealtrip.idealTrip.service.CateringService;
import com.idealtrip.idealTrip.service.DestinationService;
import com.idealtrip.idealTrip.service.HouseService;
import com.idealtrip.idealTrip.service.ReviewService;
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
    return "catering";
  }

  @GetMapping("/tourism/{id}")
  public String servicesAllTourism(Model model, @PathVariable Long id) {
    model.addAttribute("name", destinationService.findById(id).get().getNameDestination());
    model.addAttribute("nameDestination", tourismService.findByDestinationId(id));
    return "tourism";
  }

  @GetMapping("/place/{id}/image")
  public ResponseEntity<Object> downloadImage(@PathVariable Long id) throws SQLException {

    Optional<Tourism> tourism = tourismService.findById(id);
    if (tourism.isPresent() && tourism.get().getImageTourismFile() != null) {

      Resource file = (Resource) new InputStreamResource(tourism.get().getImageTourismFile().getBinaryStream());

      return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpg")
          .contentLength(tourism.get().getImageTourismFile().length()).body(file);

    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/host/{id}/image")
  public ResponseEntity<Object> downloadHostImage(@PathVariable long id) throws SQLException {

    Optional<House> house = houseService.findById(id);
    if (house.isPresent() && house.get().getHostImageFile() != null) {

      Resource file = new InputStreamResource((house.get().getHostImageFile()).getBinaryStream());

      return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
          .contentLength((house.get().getHostImageFile()).length()).body(file);

    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/house/{id}/image")
  public ResponseEntity<Object> downloadHouseImage(@PathVariable long id) throws SQLException {

    Optional<House> house = houseService.findById(id);
    if (house.isPresent() && house.get().getImagesHouseFile() != null) {

      Resource file = new InputStreamResource((house.get().getImagesHouseFile()).getBinaryStream());

      return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
          .contentLength((house.get().getImagesHouseFile()).length()).body(file);

    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/review/{id}")
  public String addReview(Model model,
      @PathVariable Long id,
      @RequestParam String titleReview,
      @RequestParam int ratingReview,
      @RequestParam String contentReview) {

    Destination currentDestination = destinationService.findDestinationById(id).orElse(null);
    Review review = new Review(currentUser, currentDestination, titleReview, ratingReview,
        contentReview);
    reviewService.save(review);
    long destinationId = destinationService.findById(id).get().getId();
    return "redirect:/review/" + destinationId;
  }

  @GetMapping("/review/{id}")
  public String getReviewsByDestination(@PathVariable Long id, Model model,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "5") int size) {

    Destination currentDestination = destinationService.findDestinationById(id).orElse(null);

    model.addAttribute("reviews", reviewService.findReviewsByDestination(currentDestination,
        PageRequest.of(page, size, Sort.by("id").descending())));
    int currentPage = page + 1;
    model.addAttribute("currentPage", currentPage);
    return "review";
  }

  @GetMapping("/house/{id}")
  public String houseByDestinationId(@PathVariable Long id, Model model) {
    model.addAttribute("houses",
        houseService.findByDestinationName(destinationService.findById(id).get().getNameDestination()));
    return "house";
  }

  @GetMapping("/review/avatar/{id}")
  @ResponseBody
  public byte[] getUserAvatar(@PathVariable Long id) throws SQLException {
    User user = userService.findById(id).orElse(null);
    if (user != null && user.getProfileAvatarFile() != null) {
      Blob avatarBlob = user.getProfileAvatarFile();
      return avatarBlob.getBytes(1, (int) avatarBlob.length());
    }
    return null;
  }

  @GetMapping("/catering/{id}/image")
  public ResponseEntity<Object> downloadCateringImage(@PathVariable long id) throws SQLException {

    Optional<Catering> catering = cateringService.findById(id);
    if (catering.isPresent() && catering.get().getImageFoodFile() != null) {

      Resource file = new InputStreamResource(catering.get().getImageFoodFile().getBinaryStream());

      return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
          .contentLength(catering.get().getImageFoodFile().length()).body(file);

    } else {
      return ResponseEntity.notFound().build();
    }
  }

}