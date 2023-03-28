package com.idealtrip.idealTrip.controller.DTOS;

public class DestinationDTO {
    private String nameDestination;
    private String contentDestination;
    private float price;
    private String titleImage;

    public String getNameDestination() {
        return nameDestination;
    }

    public DestinationDTO() {
    }

    public void setNameDestination(String nameDestination) {
        this.nameDestination = nameDestination;
    }

    public String getContentDestination() {
        return contentDestination;
    }

    public void setContentDestination(String contentDestination) {
        this.contentDestination = contentDestination;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public DestinationDTO(String nameDestination, String contentDestination, float price) {
        this.nameDestination = nameDestination;
        this.contentDestination = contentDestination;
        this.price = price;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

}
