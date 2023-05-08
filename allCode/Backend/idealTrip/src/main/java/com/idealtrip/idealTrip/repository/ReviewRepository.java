package com.idealtrip.idealTrip.repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.model.Review;
import com.idealtrip.idealTrip.model.User;


public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByUserId(Long userId);
    Page<Review> findByUser(User user, Pageable pageable);
    List<Review> findByDestinationId(Long destinationId);
    Page<Review> findByDestinationId (Long destinationId, Pageable page); 
    List<Review> findByDestination(Destination destination);
    Page<Review> findByDestination(Destination destination, Pageable pageable);

}
