package com.idealtrip.idealTrip.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
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

    public void deleteById(Long id) {
        destinations.deleteById(id);
    }

    public Optional<Destination> findById(Long id) {
        return destinations.findById(id);
    }

    public void save(Destination destination){
        this.destinations.save(destination);
    }

    // public List<Destination> findByName(String name, Pageable pageable){
    //     return (List<Destination>) extracted(name, pageable);
    // }

    // private Page<Destination> findByName(String name, Pageable pageable) {
    //     return destinations.findByName(name, pageable);
    // }
    
    
    
}
