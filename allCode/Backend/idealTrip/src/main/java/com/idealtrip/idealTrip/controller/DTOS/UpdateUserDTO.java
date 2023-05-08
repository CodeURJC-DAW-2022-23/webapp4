package com.idealtrip.idealTrip.controller.DTOS;

import java.sql.Blob;

public class UpdateUserDTO {
    private String name; 
    private String lastName;
    private Blob profileAvatarFile;

    public Blob getProfileAvatarFile() {
        return profileAvatarFile;
    }

    public void setProfileAvatarFile(Blob profileAvatarFile) {
        this.profileAvatarFile = profileAvatarFile;
    }

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
