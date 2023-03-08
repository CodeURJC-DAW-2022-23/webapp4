package com.idealtrip.idealTrip.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Page<Review> findReviewByUser(User user, Pageable pageable) {
        return reviews.findReviewByUser(user, pageable);
    }

    public Optional<Review> findById(Long id) {
        return reviews.findById(id);
    }

    public <S extends Review> S save(S entity) {
        return reviews.save(entity);
    }

    public List<Review> findReviewsByDestinationId(Long destinationId) {
        return reviews.findByDestinationId(destinationId);
    }

    public List<Review> findReviewsByNameDestination(String nameDestination) {
        return reviews.findByNameDestination(nameDestination);
    }

}
