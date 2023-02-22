package com.idealtrip.idealTrip.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.repository.DestinationRepository;

@Service
public class DestinationService {
    @Autowired
    private DestinationRepository purchases;

    public List<Destination> findAll() {
        return purchases.findAll();
    }

    public void deleteById(Long id) {
        purchases.deleteById(id);
    }

    public Optional<Destination> findById(Long id) {
        return purchases.findById(id);
    }

    public <S extends Destination> S save(S entity) {
        return purchases.save(entity);
    }
    
}
