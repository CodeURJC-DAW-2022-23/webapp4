package com.idealtrip.idealTrip.model;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity(name = "tourismTable")
public class Tourism {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameTourism;
    private String contentTourism;
    // private Destination destination;

    @Lob
    private Blob imageTourism;

    public Tourism() {
    }

    public Tourism(Long id, String nameTourism, String contentTourism, Destination destination, Blob imageTourism) {
        this.id = id;
        this.nameTourism = nameTourism;
        this.contentTourism = contentTourism;
        // this.destination = destination;
        this.imageTourism = imageTourism;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTourism() {
        return nameTourism;
    }

    public void setNameTourism(String nameTourism) {
        this.nameTourism = nameTourism;
    }

    public String getContentTourism() {
        return contentTourism;
    }

    public void setContentTourism(String contentTourism) {
        this.contentTourism = contentTourism;
    }

    // public Destination getDestination() {
    //     return destination;
    // }

    // public void setDestination(Destination destination) {
    //     this.destination = destination;
    // }

    public Blob getImageTourism() {
        return imageTourism;
    }

    public void setImageTourism(Blob imageTourism) {
        this.imageTourism = imageTourism;
    }

    
}
