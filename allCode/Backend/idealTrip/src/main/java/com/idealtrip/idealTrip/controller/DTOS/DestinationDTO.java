package com.idealtrip.idealTrip.controller.DTOS;

import org.springframework.web.multipart.MultipartFile;

public class DestinationDTO {
    private String nameDestination;
    private String contentDestination;
    private float price;
    private MultipartFile titleImage;

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

    public MultipartFile getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(MultipartFile titleImage) {
        this.titleImage = titleImage;
    }

   
   

}
