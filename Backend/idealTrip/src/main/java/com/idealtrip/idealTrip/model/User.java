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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name = "userTable")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;

    private String name;
    private String lastName;
    private String encodedPassword;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @Lob
    @JsonIgnore
    private Blob profileAvatarFile;
    private String profileAvatar;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Purchase> purchases = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();

    public User() {

    }

    public User(String email, String name, String lastName, String encodedPassword) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.encodedPassword = encodedPassword;
    }

    public User(String name, String encodedPassword, String ... roles) {
        this.name = name;
        this.encodedPassword = encodedPassword;
        this.roles = List.of(roles);
    }


    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public Blob getProfileAvatarFile() {
        return profileAvatarFile;
    }

    public void setProfileAvatarFile(Blob profileAvatarFile) {
        this.profileAvatarFile = profileAvatarFile;
    }

    public String getProfileAvatar() {
        return this.profileAvatar;
    }

    public void setProfileAvatar(String profileAvatar) {
        this.profileAvatar = profileAvatar;
    }

    public List<String> getRoles() {
        
        return this.roles;
    }

    public void setRoles(String ... roles) {
        this.roles = List.of(roles);
    }


}