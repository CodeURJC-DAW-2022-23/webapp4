package com.idealtrip.idealTrip.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public List<Catering> findAll() {
        return caterings.findAll();
    }

    public List<Catering> findByNameDestination(String name) {
        return caterings.findByNameDestination(name);
    }
    public List<Catering> findByDestination(Long id) {
        return caterings.findByDestinationId(id);
    }

    public Page<Catering> findByDestination(Long id, Pageable page) {
        return caterings.findByDestinationId(id, page);
    }

}