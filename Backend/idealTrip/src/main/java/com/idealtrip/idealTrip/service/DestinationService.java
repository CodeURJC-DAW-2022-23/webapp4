package com.idealtrip.idealTrip.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.repository.DestinationRepository;

@Service
public class DestinationService {
    @Autowired
    private DestinationRepository destinations;


    public List<Destination> findAll() {
        return destinations.findAll();
    }

    public Page<Destination> findAllPageable(Pageable pageable){
        return destinations.findAll(pageable);
    }

    public void deleteById(Long id) {
        destinations.deleteById(id);
    }

    public Optional<Destination> findById(Long id) {
        return destinations.findById(id);
    }

    public void save(Destination destination){
        this.destinations.save(destination);
    }
    

    public Optional<Destination> findDestinationById(Long id) {
        return destinations.findById(id);
    }

    public Destination findByNameDestination(String nameDestination) {
        return destinations.findBynameDestination(nameDestination);
    }

    
    
}
