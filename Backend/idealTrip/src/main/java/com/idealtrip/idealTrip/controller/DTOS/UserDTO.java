package com.idealtrip.idealTrip.controller.DTOS;

public class UserDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String passwordEncoded;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordEncoded() {
        return passwordEncoded;
    }

    public void setPasswordEncoded(String passwordEncoded) {
        this.passwordEncoded = passwordEncoded;
    }

    public UserDTO(String firstName, String lastName, String email, String passwordEncoded) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordEncoded = passwordEncoded;
    }



}