package com.idealtrip.idealTrip.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity(name = "purchaseTable")
public class Purchase {
    public interface Basic {}

    public interface Advanced {}

    @Id
    @JsonView(Purchase.Basic.class)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;
    
    @ManyToOne
    private House house;

    public Purchase() {
    }

    public Purchase(User user) {
        this.user = user;
    }


    public Purchase(User user, House currentHouse) {
        this.user = user;
        this.house = currentHouse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

}