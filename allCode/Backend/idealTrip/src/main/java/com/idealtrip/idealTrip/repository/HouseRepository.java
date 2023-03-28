package com.idealtrip.idealTrip.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.idealtrip.idealTrip.model.House;


public interface HouseRepository extends JpaRepository<House, Long>{

    List<House> findByDestinationId(Long id);
    List<House> findByDestinationName(String destinationName);
}