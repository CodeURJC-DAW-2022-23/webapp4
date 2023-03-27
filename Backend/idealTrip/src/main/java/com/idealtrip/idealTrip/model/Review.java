package com.idealtrip.idealTrip.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity(name = "reviewTable")
public class Review {
    public interface Basic {}
    public interface Advanced {}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Basic.class)
    private Long id;
    @JsonView(Basic.class)
    private String titleReview;
    @JsonView(Basic.class)
    private int ratingReview;

    @JsonView(Basic.class)
    @Column(columnDefinition = "TEXT")
    private String contentReview;

    @ManyToOne
    private User user;

    @ManyToOne
    private Destination destination;
    
    public Review() {
    }

    public Review(User user, Destination destination, String titleReview, int ratingReview,
            String contentReview) {
        this.user = user;
        this.destination = destination;
        this.titleReview = titleReview;
        this.ratingReview = ratingReview;
        this.contentReview = contentReview;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitleReview() {
        return titleReview;
    }

    public void setTitleReview(String titleReview) {
        this.titleReview = titleReview;
    }

    public int getRatingReview() {
        return ratingReview;
    }

    public void setRatingReview(int ratingReview) {
        this.ratingReview = ratingReview;
    }

    public String getContentReview() {
        return contentReview;
    }

    public void setContentReview(String contentReview) {
        this.contentReview = contentReview;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}