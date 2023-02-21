package com.idealtrip.idealTrip.model;

import java.sql.Blob;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "destinationTable")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @JsonIgnore
    private Blob imageDestination;
    private String contentDestination;

    private String nameDestination;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> prices;

    public Destination() {
    }

    public Destination(Long id, Blob imageDestination, String contentDestination, String nameDestination,
            List<String> prices) {
        this.id = id;
        this.imageDestination = imageDestination;
        this.contentDestination = contentDestination;
        this.nameDestination = nameDestination;
        this.prices = prices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Blob getImageDestination() {
        return imageDestination;
    }

    public void setImageDestination(Blob imageDestination) {
        this.imageDestination = imageDestination;
    }

    public String getContentDestination() {
        return contentDestination;
    }

    public void setContentDestination(String contentDestination) {
        this.contentDestination = contentDestination;
    }

    public String getNameDestination() {
        return nameDestination;
    }

    public void setNameDestination(String nameDestination) {
        this.nameDestination = nameDestination;
    }

    public List<String> getPrices() {
        return prices;
    }

    public void setPrices(List<String> prices) {
        this.prices = prices;
    }

}
