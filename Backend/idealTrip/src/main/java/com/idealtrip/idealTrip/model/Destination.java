package com.idealtrip.idealTrip.model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;


@Entity(name = "destinationTable")
public class Destination {

    public interface Basico {}
    public interface Avanzada {}

    public interface Todo extends Basico, Avanzada {}


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Basico.class)
    private Long id;

    @Lob
    @JsonIgnore
    private Blob titleImageFile;
    @JsonView(Basico.class)
    private String titleImage;

    @Column(name = "contentDestination", columnDefinition = "TEXT")
    @JsonView(Basico.class)
    private String contentDestination;
    @JsonView(Basico.class)
    private String nameDestination;

    @OneToOne(mappedBy = "destination", cascade = CascadeType.ALL)
    @JsonView(Avanzada.class)
    private House house;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    @JsonView(Avanzada.class)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    @JsonView(Avanzada.class)
    private List<Catering> catering;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    @JsonView(Avanzada.class)
    private List<Tourism> tourism;

    @JsonView(Basico.class)
    private float price;

    public Destination() {
    }

    public Destination(Long id, String contentDestination, String nameDestination,
            List<Review> reviews, float price) {
        this.id = id;
        this.contentDestination = contentDestination;
        this.nameDestination = nameDestination;
        this.reviews = reviews;
        this.price = price;
    }

    public Blob getTitleImageFile() {
        return titleImageFile;
    }

    public void setTitleImageFile(Blob titleImageFile) {
        this.titleImageFile = titleImageFile;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
        
}