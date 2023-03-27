package com.idealtrip.idealTrip.controller.rest;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonView;
import com.idealtrip.idealTrip.model.Catering;
import com.idealtrip.idealTrip.service.CateringService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.service.DestinationService;
@RestController
@RequestMapping("/api/destinations")
public class CitiesRestController {
    @Autowired
    private DestinationService destinations;
    private CateringService catering;

    @GetMapping("/")
    @JsonView(Destination.Basico.class)
    public Collection<Destination> getDestinations(){
        return destinations.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(Destination.Basico.class)
    public ResponseEntity<Destination> getDestiantion(@PathVariable long id){
        Optional<Destination> dest = destinations.findById(id);
        if (dest.isPresent()){
            Destination destaux = dest.get();
            return new ResponseEntity<>(destaux, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}/catering/")
    @JsonView(Destination.Basico.class)
    public Collection<Catering> getCatering(@PathVariable long id){
        return catering.findAll();
    }
}
