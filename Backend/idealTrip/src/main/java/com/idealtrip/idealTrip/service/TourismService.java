package com.idealtrip.idealTrip.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idealtrip.idealTrip.model.Tourism;
import com.idealtrip.idealTrip.repository.TourismRepository;

@Service
public class TourismService {
    @Autowired
    private TourismRepository tourisms;

    public void save(Tourism tourism){
        this.tourisms.save(tourism);
    }

    public List<Tourism> findAll() {
        return tourisms.findAll();
    }

    public Optional<Tourism> findById(Long id) {
        return tourisms.findById(id);
    }

    public void delete(Tourism entity) {
        tourisms.delete(entity);
    }
    
    
}
