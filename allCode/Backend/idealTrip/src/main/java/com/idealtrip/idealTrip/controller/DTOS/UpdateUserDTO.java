package com.idealtrip.idealTrip.controller.DTOS;

public class UpdateUserDTO {
    private String name; 
    private String lastName;


    public UpdateUserDTO() {
    }
   
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    } 

    
}
