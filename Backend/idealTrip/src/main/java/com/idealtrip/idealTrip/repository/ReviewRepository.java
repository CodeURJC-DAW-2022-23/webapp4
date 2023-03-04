package com.idealtrip.idealTrip.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import com.idealtrip.idealTrip.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByUserId(Long id);
    // Optional<Review> findByUserAndDestination(User user, Destination destination);
    // @Query("SELECT")
    // List<Review>findByDestination(Destination destination);
    // Page<Review> findByDestination (Destination destination, Pageable page);
}
