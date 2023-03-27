package com.idealtrip.idealTrip.controller.rest;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.io.IOException;
import java.lang.StackWalker.Option;
import java.net.URI;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.service.DestinationService;
@RestController
@RequestMapping("/api/destinations")
public class CitiesRestController {
    @Autowired
    private DestinationService destinations;

    @GetMapping("/")
    public Collection<Destination> getDestinations(){
        return destinations.findAll();
    }

    @GetMapping("/{id}")
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
    public List<Review> getReview(@PathVariable Long id, Pageable page){
        return review.findByDestination(id, page).getContent();
    }
    public Collection<Review> getReviews(@PathVariable long id){
        return review.findByDestination(id);
    }
    //show specific review
    @GetMapping("/reviews/{id}/{idreview}")
    @JsonView(Review.Basic.class)
    public Optional<Review> getReview(@PathVariable long id, @PathVariable long idreview) {
        review.findByDestination(id);
        return this.review.findById(idreview);
    }
    //Delete specific review
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


    /* @GetMapping("/rating")
    public List<Double> ratingDestination() {
    
    List<Destination> destinationList = destinations.findAll();
    List<Double> totalRatingList = new ArrayList<>();
    for (Destination destination : destinationList) {
      double totalRating = review.getTotalRatingForDestination(destination);
      totalRatingList.add(totalRating);
    }
    return totalRatingList;
} */

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
