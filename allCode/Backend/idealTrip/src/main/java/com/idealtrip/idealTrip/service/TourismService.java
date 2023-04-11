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
import com.idealtrip.idealTrip.model.Tourism;
import com.idealtrip.idealTrip.repository.TourismRepository;

@Service
public class TourismService {
    @Autowired
    private TourismRepository tourisms;

    @Autowired
    private DestinationService destinationService;

    public void save(Tourism tourism) {
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

    public List<Tourism> findByDestinationId(Long id) {
        return tourisms.findByDestinationId(id);
    }

    public Page<Tourism> findByDestinationId(Long id, Pageable page) {
        return tourisms.findByDestinationId(id, page);
    }

    public ResponseEntity<Resource> downloadTourismPlaceImages(long id, int index)
			throws SQLException {
		Optional<Destination> destination = destinationService.findById(id);
		if (destination.isPresent() && destination.get().getTourism().get(index).getImageTourismFile() != null) {
			Resource file = new InputStreamResource(
					destination.get().getTourism().get(index).getImageTourismFile().getBinaryStream());
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(destination.get().getTourism().get(index - 1).getImageTourismFile().length()).body(file);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}