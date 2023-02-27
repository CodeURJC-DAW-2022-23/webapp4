package com.idealtrip.idealTrip.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idealtrip.idealTrip.model.House;

public interface HouseRepository extends JpaRepository<House, Long>{
    // Optional<House> findByName(String name);
    
    
}