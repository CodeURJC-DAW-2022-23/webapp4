package com.idealtrip.idealTrip.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.model.Review;
import com.idealtrip.idealTrip.model.User;
import com.idealtrip.idealTrip.repository.ReviewRepository;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviews;

    public void delete(Review entity) {
        reviews.delete(entity);
    }

    public List<Review> findAll() {
        return reviews.findAll();
    }

    public int getTotalRatingForDestination(Destination destination) {
        List<Review> reviewList = reviews.findByDestination(destination);
        int totalRating = 0;
        for (Review review : reviewList) {
            totalRating += review.getRatingReview();
        } 
        return (int) totalRating / reviewList.size(); 
    }

    public Page<Review> findReviewByUser(User user, Pageable pageable) {
        return reviews.findByUser(user, pageable);
    }

    public Page<Review> findReviewsByDestination(Destination destination, Pageable pageable) {
        return reviews.findByDestination(destination, pageable);
    }

    //public Optional<Review> findById(Long id) {
    //    return reviews.findById(id);
    //}

    public void save(Review review) {
        reviews.save(review);
    }

    //public List<Review> findReviewsByDestinationId(Long destinationId) {
    //    return reviews.findByDestinationId(destinationId);
    //}

    // public List<Review> findReviewsByNameDestination(String nameDestination) {
    // return reviews.findByNameDestination(nameDestination);
    // }

}