package com.idealtrip.idealTrip.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "tourismTable")
public class Tourism {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameDestination;
    private String nameTourism;
    @Column(name = "contentTourism", columnDefinition = "TEXT")
    private String contentTourism;

    @ManyToOne
    private Destination destination;

    @Lob
    private Blob imageTourismFile;
    private String imageTourismURL;

    public Tourism() {
    }

    public Tourism(Long id, String nameTourism, String contentTourism, Destination destination, Blob imageTourismFile) {
        this.id = id;
        this.nameTourism = nameTourism;
        this.contentTourism = contentTourism;
        // this.destination = destination;
        this.imageTourismFile = imageTourismFile;
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

    public Blob getImageTourismFile() {
        return imageTourismFile;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String getImageTourismURL() {
        return imageTourismURL;
    }

    public void setImageTourismURL(String imageTourismURL) {
        this.imageTourismURL = imageTourismURL;
    }

    public void setImageTourismFile(Blob imageTourismFile) {
        this.imageTourismFile = imageTourismFile;
    }

    public String getNameDestination() {
        return nameDestination;
    }

    public void setNameDestination(String nameDestination) {
        this.nameDestination = nameDestination;
    }

    
}
