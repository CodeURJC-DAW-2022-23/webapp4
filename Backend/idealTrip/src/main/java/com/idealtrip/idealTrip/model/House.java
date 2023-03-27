package com.idealtrip.idealTrip.model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;


@Entity(name = "houseTable")
public class House {

    public interface Basico {}
    public interface Avanzada {}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Basico.class)
    private Long id;
    @JsonView(Basico.class)
    private String nameHouse;
    @Column(columnDefinition = "TEXT")
    private String contentHouse;
    @JsonView(Basico.class)
    private float price;

    @Lob
    @JsonView(Avanzada.class)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Blob> imagesHouseFile = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> imagesHouse = new ArrayList<>();
    @JsonView(Basico.class)
    private String hostName;
    @Lob
    private Blob hostImageFile;
    private String hostImage;

    @OneToOne(mappedBy = "house", cascade = CascadeType.ALL)
    private Purchase purchase;
    @OneToOne
    private Destination destination;
    @JsonView(Basico.class)
    private String destinationName;

    private String streetViewLink;

    @Column(nullable = false, length = 2048)
    private String mapsLink;

    public House() {
    }

    public House(Long id, String nameHouse, Destination destination, String contentHouse, String hostName,
            Purchase purchase, List<Blob> imagesHouseFile, List<String> imagesHouse, float price) {
        this.id = id;
        this.nameHouse = nameHouse;
        this.destination = destination;
        this.contentHouse = contentHouse;
        this.hostName = hostName;
        this.purchase = purchase;
        this.imagesHouseFile = imagesHouseFile;
        this.imagesHouse = imagesHouse;
        this.price = price;
    }

    public List<Blob> getImagesHouseFile() {
        return imagesHouseFile;
    }

    public void setImagesHouseFile(List<Blob> imagesHouseFile) {
        this.imagesHouseFile = imagesHouseFile;
    }

    public List<String> getImagesHouse() {
        return imagesHouse;
    }

    public void setImagesHouse(List<String> imagesHouse) {
        this.imagesHouse = imagesHouse;
    }

    public Blob getHostImageFile() {
        return hostImageFile;
    }

    public void setHostImageFile(Blob hostImageFile) {
        this.hostImageFile = hostImageFile;
    }

    public String getHostImage() {
        return hostImage;
    }

    public void setHostImage(String hostImage) {
        this.hostImage = hostImage;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    
    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getStreetViewLink() {
        return streetViewLink;
    }
    
    public void setStreetViewLink(String streetViewLink) {
        this.streetViewLink = streetViewLink;
    }

    public String getMapsLink() {
        return mapsLink;
    }

    public void setMapsLink(String mapsLink) {
        this.mapsLink = mapsLink;
    }

}