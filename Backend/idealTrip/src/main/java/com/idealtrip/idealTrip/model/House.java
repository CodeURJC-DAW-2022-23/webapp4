package com.idealtrip.idealTrip.model;

import java.sql.Blob;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "houseTable")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameHouse;
    private Destination destination;

    @Column(columnDefinition = "TEXT")
    private String contentHouse;


    @Lob
    @JsonIgnore
    private List<Blob> imagesHouse;

    public House() {
    }

    public House(Long id, String nameHouse, String contentHouse, Destination destination, List<Blob> imagesHouse) {
        this.id = id;
        this.nameHouse = nameHouse;
        this.contentHouse = contentHouse;
        this.destination = destination;
        this.imagesHouse = imagesHouse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameHouse() {
        return nameHouse;
    }

    public void setNameHouse(String nameHouse) {
        this.nameHouse = nameHouse;
    }

    public String getContentHouse() {
        return contentHouse;
    }

    public void setContentHouse(String contentHouse) {
        this.contentHouse = contentHouse;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public List<Blob> getImagesHouse() {
        return imagesHouse;
    }

    public void setImagesHouse(List<Blob> imagesHouse) {
        this.imagesHouse = imagesHouse;
    }

}
