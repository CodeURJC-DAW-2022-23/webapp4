package com.idealtrip.idealTrip.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idealtrip.idealTrip.model.Catering;
import com.idealtrip.idealTrip.repository.CateringRepository;

@Service
public class CateringService {
    @Autowired 
    private CateringRepository caterings;

    public void save(Catering catering){
        this.caterings.save(catering);
    }

    public Optional<Catering> findById(Long id) {
        return caterings.findById(id);
    }

    public void delete(Catering entity) {
        caterings.delete(entity);
    }

    

}