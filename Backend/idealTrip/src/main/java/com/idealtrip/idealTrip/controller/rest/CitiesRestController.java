package com.idealtrip.idealTrip.controller.rest;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.io.IOException;
import java.lang.StackWalker.Option;
import java.net.URI;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

import com.idealtrip.idealTrip.controller.DTOS.ReviewDTO;
import com.fasterxml.jackson.annotation.JsonView;
import com.idealtrip.idealTrip.model.*;
import com.idealtrip.idealTrip.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    User currentUser;

    @GetMapping("/")
    @JsonView(Destination.Basico.class)
    public Collection<Destination> getDestinations(){
        return destinations.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(Destination.Basico.class)
    public ResponseEntity<Destination> getDestiantion(@PathVariable long id){
        Optional<Destination> dest = destinations.findById(id);
        if (dest.isPresent()){
            Destination destaux = dest.get();
            return new ResponseEntity<>(destaux, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @JsonView(Destination.Basico.class)
    public ResponseEntity<Destination> deleteDestination(@PathVariable long id){
        Optional<Destination> dest = destinations.findById(id);
        if (dest.isPresent()){
            Destination destaux = dest.get();
            destinations.deleteById(id);
            return new ResponseEntity<>(destaux, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Destination> createDestination(@RequestBody Destination destination){
        destinations.save(destination);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(destination.getId()).toUri();
        return ResponseEntity.created(location).body(destination);
    }

    @GetMapping("/catering/{id}")
    @JsonView(Catering.Basic.class)
    public Collection<Catering> getCatering(@PathVariable long id){
        return catering.findByDestination(id);
    }
    @GetMapping("/tourism/{id}")
    @JsonView(Tourism.Basic.class)
    public Collection<Tourism> getTourism(@PathVariable long id){
        return tourism.findByDestinationId(id);
    }
    @GetMapping("/house/{id}")
    @JsonView(House.Basic.class)
    public Collection<House> getHouse(@PathVariable long id){
        return house.findByDestinationName(destinations.findById(id).get().getNameDestination());
    }
    @GetMapping("/reviews/{id}")
    @JsonView(Review.Basic.class)
    public Collection<Review> getReviews(@PathVariable long id){
        return review.findByDestination(id);
    }
    //Mostrar review concreta
    @GetMapping("/reviews/{id}/{idreview}")
    @JsonView(Review.Basic.class)
    public Optional<Review> getReview(@PathVariable long id, @PathVariable long idreview) {
        review.findByDestination(id);
        return this.review.findById(idreview);
    }
    @DeleteMapping("/reviews/{id}/{idreview}")
    @JsonView(Review.Basic.class)
    public ResponseEntity<Review> deleteReview(@PathVariable long id, @PathVariable long idreview){
        review.findByDestination(id);
        Optional<Review> reviDel = this.review.findById(idreview);
        if (reviDel.isPresent()) {
            this.review.deleteById(idreview);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    

    @PostMapping("/reviews/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(Review.Basic.class)
    public Review createReview(@PathVariable Long id, @RequestBody ReviewDTO newreview) {

        Destination currentDestination = destinations.findDestinationById(id).orElse(null);
        Review auxreview = new Review(currentUser, currentDestination, newreview.getReviewTitle(), newreview.getRating(), newreview.getReviewContent());
        review.save(auxreview);
        return auxreview;
    }
}
