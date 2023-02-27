package com.idealtrip.idealTrip.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.model.Review;
import com.idealtrip.idealTrip.model.User;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Optional<Review> findByUserAndDestination(User user, Destination destination);
    // @Query("SELECT")
    // List<Review>findByDestination(Destination destination);
    // Page<Review> findByDestination (Destination destination, Pageable page);
}
