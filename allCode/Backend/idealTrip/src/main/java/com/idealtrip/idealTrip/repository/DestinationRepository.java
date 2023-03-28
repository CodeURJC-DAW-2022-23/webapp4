package com.idealtrip.idealTrip.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.idealtrip.idealTrip.model.Destination;


public interface DestinationRepository extends JpaRepository<Destination, Long> {
    Optional<Destination> findDestinationById(Long id);
    Destination findBynameDestination(String nameDestination);
    Page<Destination> findAll(Pageable page); 
}

