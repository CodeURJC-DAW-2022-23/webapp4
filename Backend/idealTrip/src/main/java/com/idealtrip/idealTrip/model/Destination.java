package com.idealtrip.idealTrip.model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity(name = "destinationTable")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Blob> imageDestination = new ArrayList<>();
    private String contentDestination;

    private String nameDestination;

    // @OneToOne(mappedBy = "destino", cascade = CascadeType.ALL)
    // private House house;

    // @OneToMany(mappedBy = "destination")
    // private List<Review> reviews = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> prices;

    public Destination() {
    }

    public Destination(Long id, List<Blob> imageDestination, String contentDestination, String nameDestination,
            List<Review> reviews, List<String> prices) {
        this.id = id;
        this.imageDestination = imageDestination;
        this.contentDestination = contentDestination;
        this.nameDestination = nameDestination;
        // this.reviews = reviews;
        this.prices = prices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Blob> getImageDestination() {
        return imageDestination;
    }

    public void setImageDestination(List<Blob> imageDestination) {
        this.imageDestination = imageDestination;
    }

    // public List<Review> getReviews() {
    //     return reviews;
    // }

    // public void setReviews(List<Review> reviews) {
    //     this.reviews = reviews;
    // }

    public String getContentDestination() {
        return contentDestination;
    }

    public void setContentDestination(String contentDestination) {
        this.contentDestination = contentDestination;
    }

    public String getNameDestination() {
        return nameDestination;
    }

    public void setNameDestination(String nameDestination) {
        this.nameDestination = nameDestination;
    }

    public List<String> getPrices() {
        return prices;
    }

    public void setPrices(List<String> prices) {
        this.prices = prices;
    }

    // public Review getReview() {
    // return review;
    // }

    // public void setReview(Review review) {
    // this.review = review;
    // }

}
