package com.idealtrip.idealTrip.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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

    public Page<Destination> findAll(Pageable page) {
        return destinations.findAll(page); 
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

    public ResponseEntity<Resource> downloadImageDestination(long id) throws SQLException {
		Optional<Destination> destination = this.findById(id);
		if (destination.isPresent() && destination.get().getTitleImage() != null) {
			Resource file = new InputStreamResource(destination.get().getTitleImageFile().getBinaryStream());
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(destination.get().getTitleImageFile().length()).body(file);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

    public DestinationRepository getDestinations() {
        return destinations;
    }

    public void setDestinations(DestinationRepository destinations) {
        this.destinations = destinations;
    }

    public boolean exist(long id) {
		return destinations.existsById(id);
	}
    
    
}