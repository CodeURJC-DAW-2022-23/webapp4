package com.idealtrip.idealTrip.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.idealtrip.idealTrip.model.Tourism;


public interface TourismRepository extends JpaRepository<Tourism, Long>{
    List<Tourism> findByDestinationId(Long id);
    Page<Tourism> findByDestinationId(Long id, Pageable page); 

}
