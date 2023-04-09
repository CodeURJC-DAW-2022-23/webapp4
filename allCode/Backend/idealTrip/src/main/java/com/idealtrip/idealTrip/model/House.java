package com.idealtrip.idealTrip.model;

import java.sql.Blob;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity(name = "houseTable")
public class House {

    public interface Basic {
    }

    public interface Advanced {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Basic.class)
    private Long id;
    @JsonView(Basic.class)
    private String nameHouse;
    @JsonView(Basic.class)
    @Column(columnDefinition = "TEXT")
    private String contentHouse;
    @JsonView(Basic.class)
    private float price;

    @Lob
    @JsonView(Advanced.class)
    private Blob imagesHouseFile;

    private String imagesHouse;

    @JsonView(Basic.class)
    private String hostName;
    @Lob
    private Blob hostImageFile;
    private String hostImage;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Purchase> purchases;
    @OneToOne
    private Destination destination;
    @JsonView(Basic.class)
    private String destinationName;

    private String streetViewLink;

    @Column(nullable = false, length = 2048)
    private String mapsLink;

    public House() {
    }

    public House(Long id, String nameHouse, Destination destination, String contentHouse, String hostName,
            List<Purchase> purchase, List<Blob> imagesHouseFile, List<String> imagesHouse, float price) {
        this.id = id;
        this.nameHouse = nameHouse;
        this.destination = destination;
        this.contentHouse = contentHouse;
        this.hostName = hostName;
        this.purchases = purchase;
        this.price = price;
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

    public Blob getImagesHouseFile() {
        return imagesHouseFile;
    }

    public void setImagesHouseFile(Blob imagesHouseFile) {
        this.imagesHouseFile = imagesHouseFile;
    }

    public String getImagesHouse() {
        return imagesHouse;
    }

    public void setImagesHouse(String imagesHouse) {
        this.imagesHouse = imagesHouse;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
}