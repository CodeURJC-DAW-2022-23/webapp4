package com.idealtrip.idealTrip.model;

import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;


@Entity(name = "cateringTable")
public class Catering {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameFood;
    @Column(name = "contentFood", columnDefinition = "TEXT")
    private String contentFood;

    private String nameDestination;

    @ManyToOne
    private Destination destination;

    @Lob
    private Blob imageFoodFile;
    private String imageFoodUrl;


    public Catering(){

    }

    public Catering(Long id, String nameFood, String contentFood, Blob imageFoodFile, Destination destination) {
        this.id = id;
        this.nameFood = nameFood;
        this.contentFood = contentFood;
        this.imageFoodFile = imageFoodFile;
        this.destination = destination;
    }

    public Blob getImageFoodFile() {
        return imageFoodFile;
    }

    public void setImageFoodFile(Blob imageFoodFile) {
        this.imageFoodFile = imageFoodFile;
    }

    public String getImageFoodUrl() {
        return imageFoodUrl;
    }

    public void setImageFoodUrl(String imageFoodUrl) {
        this.imageFoodUrl = imageFoodUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameDestination() {
        return nameDestination;
    }

    public void setNameDestination(String nameDestination) {
        this.nameDestination = nameDestination;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public String getContentFood() {
        return contentFood;
    }

    public void setContentFood(String contentFood) {
        this.contentFood = contentFood;
    }

    public Blob getImageFood() {
        return imageFoodFile;
    }

    public void setImageFood(Blob imageFoodFile) {
        this.imageFoodFile = imageFoodFile;
    }



    
}