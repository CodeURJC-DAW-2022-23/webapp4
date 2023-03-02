package com.idealtrip.idealTrip.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.idealtrip.idealTrip.model.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    // @Query("SELECT d FROM Destination d WHERE LOWER(g.name)")
    // Page<Destination>findByName(String name, Pageable pageable);
    
    
}
