package com.idealtrip.idealTrip.controller.rest;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.idealtrip.idealTrip.controller.DTOS.DestinationDTO;
import com.idealtrip.idealTrip.controller.DTOS.ReviewDTO;
import com.fasterxml.jackson.annotation.JsonView;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.idealtrip.idealTrip.model.*;
import com.idealtrip.idealTrip.model.Review.Basic;
import com.idealtrip.idealTrip.repository.DestinationRepository;
import com.idealtrip.idealTrip.service.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/destinations")
public class CitiesRestController {
    @Autowired
    private DestinationService destinations;
    @Autowired
    private CateringService caterings;
    @Autowired
    private TourismService tourisms;
    @Autowired
    private HouseService houses;
    @Autowired
    private ReviewService reviews;
    @Autowired
    private UserService userService;

    @Autowired
    private DestinationRepository destinationRepository;

    User currentUser;

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();

        if (principal != null) {
            userService.findByEmail(principal.getName()).ifPresent(us -> currentUser = us);
            model.addAttribute("curretUser", currentUser);

        } else {
            model.addAttribute("logged", false);
        }
    }

    @Operation(summary = "Get all destinations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all Destinations", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Destination.class)) }),
            @ApiResponse(responseCode = "404", description = "Destinations not found", content = @Content)
    })
    @GetMapping("/")
    @JsonView(Destination.Basico.class)
    public Page<Destination> getDestinations(Pageable page) {
        return destinations.findAll(page);
    }

    @Operation(summary = "Get one destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Destination", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Destination.class)) }),
            @ApiResponse(responseCode = "404", description = "Destination not found", content = @Content)
    })
    @GetMapping("/{id}")
    @JsonView(Destination.Basico.class)
    public ResponseEntity<Destination> getDestiantion(@PathVariable long id) {
        Optional<Destination> dest = destinations.findById(id);
        if (dest.isPresent()) {
            Destination destaux = dest.get();
            return new ResponseEntity<>(destaux, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Destination deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Destination.class)) }),
            @ApiResponse(responseCode = "404", description = "Destination not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbiden or don't have permissions", content = @Content) })
    @DeleteMapping("/{id}")
    @JsonView(Destination.Basico.class)
    public ResponseEntity<Destination> deleteDestination(@PathVariable long id) {
        Optional<Destination> dest = destinations.findById(id);
        if (dest.isPresent()) {
            Destination destaux = dest.get();
            destinations.deleteById(id);
            return new ResponseEntity<>(destaux, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Add a destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Destination added to the application", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Destination.class)) }),
            @ApiResponse(responseCode = "404", description = "Destination not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbiden or don't have permissions", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Destination> createDestination(@RequestBody DestinationDTO destinationDTO) {
        Destination destination = new Destination(destinationDTO);
        // try {
        // Resource image = new ClassPathResource(destination.getTitleImage());
        // destination.setTitleImageFile(BlobProxy.generateProxy(image.getInputStream(),
        // image.contentLength()));
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        destinations.save(destination);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(destination.getId()).toUri();
        return ResponseEntity.created(location).body(destination);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDestination(
            @RequestParam("destinationName") String name,
            @RequestParam("destinationContent") String content,
            @RequestParam("price") int price,
            @RequestParam(value = "destinationImageFile", required = false) MultipartFile destinationImageFile) {
        Destination destination = new Destination();
        destination.setNameDestination(name);
        destination.setContentDestination(content);
        destination.setPrice(price);

        try {
            if (destinationImageFile != null) {
                byte[] imageBytes = destinationImageFile.getBytes();
                Blob imageBlob = new SerialBlob(imageBytes);
                destination.setTitleImageFile(imageBlob);
            }
            destinations.save(destination);
            return ResponseEntity.ok().build();
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<Resource> createDestinationImage(@PathVariable long id, @RequestParam MultipartFile image,
            HttpServletRequest request) throws URISyntaxException, SQLException, IOException {
        Optional<Destination> destination = destinations.findById(id);
        if (destination.isPresent() && image != null && !image.isEmpty()) {
            Destination newDestination = destination.get();
            try {
                newDestination.setTitleImageFile(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
                newDestination.setTitleImage("");
                destinations.save(newDestination);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).build()
                    .toUriString();
            Resource file = new InputStreamResource(image.getInputStream());

            URI location = new URI(baseUrl + "/api/destinations/" + id + "/image");
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "image/jpeg", HttpHeaders.CONTENT_LOCATION, location.toString())
                    .contentLength(newDestination.getTitleImageFile().length()).body(file);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Operation(summary = "Get all caterings of one destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all catering of destination", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Destination.class)) }),
            @ApiResponse(responseCode = "404", description = "Catering of destination not found", content = @Content) })
    @GetMapping("/catering/{id}")
    @JsonView(Catering.Basic.class)
    public Page<Catering> getCatering(@PathVariable long id, Pageable page) {
        return caterings.findByDestination(id, page);
    }

    @Operation(summary = "Get all torism of one destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all tourism of destination", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Destination.class)) }),
            @ApiResponse(responseCode = "404", description = "Tourism of destination not found", content = @Content) })
    @GetMapping("/tourism/{id}")
    @JsonView(Tourism.Basic.class)
    public Page<Tourism> getTourism(@PathVariable long id, Pageable page) {
        return tourisms.findByDestinationId(id, page);
    }

    @Operation(summary = "Get all houses of one destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all houses of destinations", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Destination.class)) }),
            @ApiResponse(responseCode = "404", description = "Houses of destination not found", content = @Content) })
    @GetMapping("/house/{id}")
    @JsonView(House.Basic.class)
    public Collection<House> getHouse(@PathVariable long id) {
        return houses.findByDestinationName(destinations.findById(id).get().getNameDestination());
    }

    @GetMapping("/reviews")
    @JsonView(Review.Basic.class)
    public List<Review> getAllReviews() {
        return reviews.findAll();
    }

    @Operation(summary = "Get all reviews of one destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all reviews of one destination", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Destination.class)) }),
            @ApiResponse(responseCode = "404", description = "Reviews of destination not found", content = @Content) })
    @GetMapping("/reviews/{id}")
    @JsonView(Review.Basic.class)
    public Page<Review> getReview(@PathVariable Long id, Pageable page) {
        return reviews.findByDestination(id, page);
    }

    // show specific review
    @Operation(summary = "Get one review of a specific destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the review of the destination", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Destination.class)) }),
            @ApiResponse(responseCode = "404", description = "Review of the destination not found", content = @Content) })
    @GetMapping("/reviews/{id}/comment/{idreview}")
    @JsonView(Review.Basic.class)
    public Optional<Review> getReview(@PathVariable long id, @PathVariable long idreview) {
        reviews.findByDestination(id);
        return this.reviews.findById(idreview);
    }

    // Delete specific review
    @Operation(summary = "Delete a review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Review.class)) }),
            @ApiResponse(responseCode = "404", description = "Review not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbiden or don't have permissions", content = @Content) })
    @DeleteMapping("/reviews/{id}/comment/{idreview}")
    @JsonView(Review.Basic.class)
    public ResponseEntity<Review> deleteReview(@PathVariable long id, @PathVariable long idreview) {
        reviews.findByDestination(id);
        Optional<Review> reviDel = this.reviews.findById(idreview);
        if (reviDel.isPresent()) {
            this.reviews.deleteById(idreview);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a review")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Review deleted"),
            @ApiResponse(responseCode = "404", description = "Review not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden or don't have permissions")
    })
    @DeleteMapping("/reviews/{id}")
    @JsonView(Review.Basic.class)
    public ResponseEntity<Review> deleteOneReview(@PathVariable long id) {
        Optional<Review> review = this.reviews.findById(id);
        if (review.isPresent()) {
            reviews.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Add a review to a destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Review added", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Review.class)) }),
            @ApiResponse(responseCode = "404", description = "Destination not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbiden or don't have permissions", content = @Content) })
    @PostMapping("/reviews/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(Review.Basic.class)
    public Review createReview(@PathVariable Long id, @RequestBody ReviewDTO newreview) {

        Destination currentDestination = destinations.findDestinationById(id).orElse(null);
        Review auxreview = new Review(currentUser, currentDestination, newreview.getReviewTitle(),
                newreview.getRating(), newreview.getReviewContent());
        reviews.save(auxreview);
        return auxreview;
    }

    @Operation(summary = "Get average of each destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found average of each destination", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Destination.class)) }),
            @ApiResponse(responseCode = "404", description = "Average destinationss not found", content = @Content) })
    @GetMapping("/rating")
    public List<Map<String, Object>> ratingDestination() {

        List<Destination> destinationList = destinations.findAll();
        List<Map<String, Object>> cityRatingList = new ArrayList<>();

        for (Destination destination : destinationList) {
            double totalRating = reviews.getTotalRatingForDestination(destination);
            String cityName = destination.getNameDestination();
            Map<String, Object> cityRatingMap = new HashMap<>();
            cityRatingMap.put("cityName", cityName);
            cityRatingMap.put("totalRating", totalRating);
            cityRatingList.add(cityRatingMap);
        }
        return cityRatingList;
    }

    @Operation(summary = "Get image of the destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found image of destination", content = {
                    @Content(mediaType = "image/jpeg", schema = @Schema(implementation = Destination.class)) }),
            @ApiResponse(responseCode = "404", description = "Image not found", content = @Content) })
    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> downloadImage(@PathVariable long id) throws SQLException {
        Optional<Destination> destination = destinations.findById(id);

        if (destination.isPresent() && destination.get().getTitleImageFile() != null) {
            Resource file = new InputStreamResource(destination.get().getTitleImageFile().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(destination.get().getTitleImageFile().length()).body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/catering/{id}/image")
    public ResponseEntity<Resource> downloadImageFood(@PathVariable long id) throws SQLException {
        Optional<Catering> catering = caterings.findById(id);

        if (catering.isPresent() && catering.get().getImageFoodFile() != null) {
            Resource file = new InputStreamResource(catering.get().getImageFoodFile().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(catering.get().getImageFoodFile().length()).body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tourism/{id}/image")
    public ResponseEntity<Resource> downloadImagePlace(@PathVariable long id) throws SQLException {
        Optional<Tourism> tourism = tourisms.findById(id);

        if (tourism.isPresent() && tourism.get().getImageTourismFile() != null) {
            Resource file = new InputStreamResource(tourism.get().getImageTourismFile().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(tourism.get().getImageTourismFile().length()).body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/house/{id}/image")
    public ResponseEntity<Resource> downloadImageHouse(@PathVariable long id) throws SQLException {
        Optional<House> house = houses.findById(id);

        if (house.isPresent() && house.get().getImagesHouseFile() != null) {
            Resource file = new InputStreamResource(house.get().getImagesHouseFile().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(house.get().getImagesHouseFile().length()).body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/house/host/{id}/image")
    public ResponseEntity<Resource> downloadImageHost(@PathVariable long id) throws SQLException {
        Optional<House> house = houses.findById(id);

        if (house.isPresent() && house.get().getHostImageFile() != null) {
            Resource file = new InputStreamResource(house.get().getHostImageFile().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(house.get().getHostImageFile().length()).body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/editDestination/{id}")
    public ResponseEntity<String> updateUser(@PathVariable long id,
            @RequestParam("destinationName") String nameDestination,
            @RequestParam("destinationContent") String contentDestination,
            @RequestParam("price") int price,
            @RequestParam(value = "destinationImageFile", required = false) MultipartFile destinationImageFile) {
        try {
            Destination destination = destinations.findById(id).orElseThrow();
            destination.setNameDestination(nameDestination);
            destination.setContentDestination(contentDestination);
            destination.setPrice(price);
            if (destinationImageFile != null) {
                byte[] imageBytes = destinationImageFile.getBytes();
                Blob imageBlob = new SerialBlob(imageBytes);
                destination.setTitleImageFile(imageBlob);
            }

            destinationRepository.save(destination);
            return ResponseEntity.ok("Destination updated successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating destination");
        }
    }
}
