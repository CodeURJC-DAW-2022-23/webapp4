package com.idealtrip.idealTrip.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idealtrip.idealTrip.model.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    // @Query("SELECT d FROM Destination d WHERE LOWER(g.name)")
    // Page<Destination>findByName(String name, Pageable pageable);
    Optional<Destination> findDestinationById(Long id);
    Destination findBynameDestination(String nameDestination);
    
}
