package com.idealtrip.idealTrip.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.idealtrip.idealTrip.model.Catering;


public interface CateringRepository extends JpaRepository<Catering, Long> {
    List<Catering> findByNameDestination(String name);

    List<Catering> findByDestinationId(Long id);
    Page<Catering> findByDestinationId(Long id, Pageable page);
}