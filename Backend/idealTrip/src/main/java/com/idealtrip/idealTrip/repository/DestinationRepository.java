package com.idealtrip.idealTrip.repository;

import java.util.List;
// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import com.idealtrip.idealTrip.model.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    // List<Destination>findDestination(String name);
    
    
}
