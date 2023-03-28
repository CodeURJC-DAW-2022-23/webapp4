package com.idealtrip.idealTrip.controller.DTOS;

public class ReviewDTO {
    private String reviewTitle;
    private String reviewContent;
    private int rating;

    public ReviewDTO() {

    }

    public ReviewDTO(String reviewTitle, String reviewContent, int rating) {
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.rating = rating;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public int getRating() {
        return rating;
    }
}
