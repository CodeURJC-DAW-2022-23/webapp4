package com.idealtrip.idealTrip.controller.rest;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;
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
import com.idealtrip.idealTrip.model.*;
import com.idealtrip.idealTrip.service.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses; 

@RestController
@RequestMapping("/api/destinations")
public class CitiesRestController {
    @Autowired
    private DestinationService destinations;
    @Autowired
    private CateringService catering;
    @Autowired
    private TourismService tourism;
    @Autowired
    private HouseService house;
    @Autowired
    private ReviewService review;
    @Autowired
    private UserService userService;

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
        try {
            Resource image = new ClassPathResource(destination.getTitleImage());
            destination.setTitleImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        destinations.save(destination);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(destination.getId()).toUri();
        return ResponseEntity.created(location).body(destination);
    }


    @Operation(summary = "Get all caterings of one destination")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Found all catering of destination", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = Destination.class)) }),
                        @ApiResponse(responseCode = "404", description = "Catering of destination not found", content = @Content) })
    @GetMapping("/catering/{id}")
    @JsonView(Catering.Basic.class)
    public Collection<Catering> getCatering(@PathVariable long id) {
        return catering.findByDestination(id);
    }


    @Operation(summary = "Get all torism of one destination")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Found all tourism of destination", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = Destination.class)) }),
                        @ApiResponse(responseCode = "404", description = "Tourism of destination not found", content = @Content) })
    @GetMapping("/tourism/{id}")
    @JsonView(Tourism.Basic.class)
    public Collection<Tourism> getTourism(@PathVariable long id) {
        return tourism.findByDestinationId(id);
    }


    @Operation(summary = "Get all houses of one destination")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Found all houses of destinations", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = Destination.class)) }),
                        @ApiResponse(responseCode = "404", description = "Houses of destination not found", content = @Content) })
    @GetMapping("/house/{id}")
    @JsonView(House.Basic.class)
    public Collection<House> getHouse(@PathVariable long id) {
        return house.findByDestinationName(destinations.findById(id).get().getNameDestination());
    }


    @Operation(summary = "Get all reviews of one destination")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Found all reviews of one destination", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = Destination.class)) }),
                        @ApiResponse(responseCode = "404", description = "Reviews of destination not found", content = @Content) })
    @GetMapping("/reviews/{id}")
    @JsonView(Review.Basic.class)
    public Page<Review> getReview(@PathVariable Long id, Pageable page) {
        return review.findByDestination(id, page);
    }

    // show specific review
    @Operation(summary = "Get one review of a specific destination")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Found the review of the destination", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = Destination.class)) }),
                        @ApiResponse(responseCode = "404", description = "Review of the destination not found", content = @Content) })
    @GetMapping("/reviews/{id}/{idreview}")
    @JsonView(Review.Basic.class)
    public Optional<Review> getReview(@PathVariable long id, @PathVariable long idreview) {
        review.findByDestination(id);
        return this.review.findById(idreview);
    }

    // Delete specific review
    @Operation(summary = "Delete a review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Review.class)) }),
            @ApiResponse(responseCode = "404", description = "Review not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbiden or don't have permissions", content = @Content) })
    @DeleteMapping("/reviews/{id}/{idreview}")
    @JsonView(Review.Basic.class)
    public ResponseEntity<Review> deleteReview(@PathVariable long id, @PathVariable long idreview) {
        review.findByDestination(id);
        Optional<Review> reviDel = this.review.findById(idreview);
        if (reviDel.isPresent()) {
            this.review.deleteById(idreview);
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
        review.save(auxreview);
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
            double totalRating = review.getTotalRatingForDestination(destination);
            String cityName = destination.getNameDestination();
            Map<String, Object> cityRatingMap = new HashMap<>();
            cityRatingMap.put("cityName", cityName);
            cityRatingMap.put("totalRating", totalRating);
            cityRatingList.add(cityRatingMap);
        }
        return cityRatingList;
    }

}
