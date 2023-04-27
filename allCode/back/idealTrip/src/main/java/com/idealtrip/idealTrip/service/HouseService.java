package com.idealtrip.idealTrip.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idealtrip.idealTrip.model.House;
import com.idealtrip.idealTrip.repository.HouseRepository;

@Service
public class HouseService {
    @Autowired
    private HouseRepository houses;

    public void save(House house){
        this.houses.save(house);
    }

    public List<House> findAll() {
        return houses.findAll();
    }

    public Optional<House> findById(Long id) {
        return houses.findById(id);
    }

    public void delete(House entity) {
        houses.delete(entity);
    }

    // public Optional<House> findByName(String name) {
    //     return houses.findByName(name);
    // }
    
    public List<House> findByDestinationId(Long id){
        return houses.findByDestinationId(id);
    }

    public List<House> findByDestinationName(String destinationName){
        return houses.findByDestinationName(destinationName);
    }
}