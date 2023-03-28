package com.idealtrip.idealTrip.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.model.Review;
import com.idealtrip.idealTrip.model.User;
import com.idealtrip.idealTrip.repository.ReviewRepository;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviews;

    public List<Review> findAll() {
        return reviews.findAll();
    }

    public double getTotalRatingForDestination(Destination destination) {
        List<Review> reviewList = reviews.findByDestination(destination);
        int totalRating = 0;
        for (Review review : reviewList) {
            totalRating += review.getRatingReview();
        }
        return (double) totalRating / reviewList.size();
    }

    public Page<Review> findReviewByUser(User user, Pageable pageable) {
        return reviews.findByUser(user, pageable);
    }

    public Page<Review> findReviewsByDestination(Destination destination, Pageable pageable) {
        return reviews.findByDestination(destination, pageable);
    }

    public Page<Review> findByDestination(Long id, Pageable page) {
        return reviews.findByDestinationId(id, page);
    }

    public List<Review> findByDestination(Long id) {
        return reviews.findByDestinationId(id);
    }

    public Optional<Review> findById(Long id) {
        return reviews.findById(id);
    }

    public void save(Review review) {
        reviews.save(review);
    }

    public void deleteById(long idreview) {
        reviews.deleteById(idreview);
    }

    public Page<Review> findAllReviewPage(Pageable pageable) {
        return reviews.findAll(pageable);
    }
    

}