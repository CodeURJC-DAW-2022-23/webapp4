package com.idealtrip.idealTrip.model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "destinationTable")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @JsonIgnore
    private Blob titleImageFile;
    private String titleImage;

    // @Lob
    // @ElementCollection(fetch = FetchType.LAZY)
    // private List<Blob> imageDestination = new ArrayList<>();

    @Column(name = "contentDestination", columnDefinition = "TEXT")
    private String contentDestination;

    private String nameDestination;

    @OneToOne(mappedBy = "destination", cascade = CascadeType.ALL)
    private House house;

    // @OneToMany(mappedBy = "destination")
    // private Review reviews;

    private float price;

    public Destination() {
    }

    public Destination(Long id, String contentDestination, String nameDestination,
            List<Review> reviews, float price) {
        this.id = id;
        this.contentDestination = contentDestination;
        this.nameDestination = nameDestination;
        // this.reviews = reviews;
        this.price = price;
    }

    public Blob getTitleImageFile() {
        return titleImageFile;
    }

    public void setTitleImageFile(Blob titleImageFile) {
        this.titleImageFile = titleImageFile;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // public List<Blob> getImageDestination() {
    //     return imageDestination;
    // }

    // public void setImageDestination(List<Blob> imageDestination) {
    //     this.imageDestination = imageDestination;
    // }

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    // public Review getReview() {
    // return review;
    // }

    // public void setReview(Review review) {
    // this.review = review;
    // }
        
}
