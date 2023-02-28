package com.idealtrip.idealTrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import com.idealtrip.idealTrip.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Optional<Review> findByUserAndDestination(User user, Destination destination);
    // @Query("SELECT")
    // List<Review>findByDestination(Destination destination);
    // Page<Review> findByDestination (Destination destination, Pageable page);
}
