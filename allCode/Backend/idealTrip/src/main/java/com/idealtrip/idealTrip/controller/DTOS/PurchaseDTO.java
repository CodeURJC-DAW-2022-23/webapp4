package com.idealtrip.idealTrip.controller.DTOS;

public class PurchaseDTO {
    private String nameHouse;
    private String userName;
    private Long id;

    public PurchaseDTO(Long id, String userName, String nameHouse) {
        this.userName = userName;
        this.nameHouse = nameHouse;
        this.id = id;
    }

    public String getNameHouse() {
        return nameHouse;
    }

    public void setNameHouse(String nameHouse) {
        this.nameHouse = nameHouse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
