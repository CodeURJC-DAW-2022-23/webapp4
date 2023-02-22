// package com.idealtrip.idealtrip.model;

// import java.sql.Blob;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.Lob;

// @Entity(name = "cateringTable")
// public class Catering {
    
//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Long id;

//     private String nameFood;
//     private String contentFood;
//     private Destination destination;

//     @Lob
//     private Blob imageFood;

//     public Catering(){

//     }

//     public Catering(Long id, String nameFood, String contentFood, Blob imageFood, Destination destination) {
//         this.id = id;
//         this.nameFood = nameFood;
//         this.contentFood = contentFood;
//         this.imageFood = imageFood;
//         this.destination = destination;
//     }

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getNameFood() {
//         return nameFood;
//     }

//     public void setNameFood(String nameFood) {
//         this.nameFood = nameFood;
//     }

//     public String getContentFood() {
//         return contentFood;
//     }

//     public void setContentFood(String contentFood) {
//         this.contentFood = contentFood;
//     }

//     public Blob getImageFood() {
//         return imageFood;
//     }

//     public void setImageFood(Blob imageFood) {
//         this.imageFood = imageFood;
//     }

//     public Destination getDestination() {
//         return destination;
//     }

//     public void setDestination(Destination destination) {
//         this.destination = destination;
//     }

    
// }
