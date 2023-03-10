package com.idealtrip.idealTrip.repository;



import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.idealtrip.idealTrip.model.Review;
import com.idealtrip.idealTrip.model.User;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findReviewByUser(User user, Pageable pageable);
    List<Review> findByDestinationId(Long destinationId);
    Optional<Review> findReviewByUserId(Long id);
    //List<Review> findByNameDestination(String nameDestination);

    // Optional<Review> findByUserAndDestination(User user, Destination destination);
    // @Query("SELECT")
    // List<Review>findByDestination(Destination destination);
    // Page<Review> findByDestination (Destination destination, Pageable page);

    

    
}
