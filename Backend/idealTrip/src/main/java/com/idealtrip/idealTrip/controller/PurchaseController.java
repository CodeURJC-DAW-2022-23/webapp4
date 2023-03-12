 package com.idealtrip.idealTrip.controller;

 import com.idealtrip.idealTrip.model.House;
 import com.idealtrip.idealTrip.model.Purchase;
 import com.idealtrip.idealTrip.model.Review;
 import com.idealtrip.idealTrip.service.HouseService;
 import com.idealtrip.idealTrip.service.NewsletterService;
 import com.idealtrip.idealTrip.service.PurchaseService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestParam;

 import java.util.Optional;

 @Controller
 public class PurchaseController {
     @Autowired
     private HouseService houseService;
     @Autowired
     private PurchaseService purchaseService;

     private Optional<House> house;
    @GetMapping("/purchase/{id}")
    public String purchase (Model model,@PathVariable Long id){
        house = houseService.findById(id);
        model.addAttribute("house", house.get());
        return "purchase";
    }
     @PostMapping("/purchaseMail")
     public String sendMailPurhcase(Model model, Purchase purchase){
         String to = purchase.getUser().getEmail();
         Float precio = purchase.getHouse().getPrice();
         String destino = purchase.getHouse().getDestinationName();
         String hotel = purchase.getHouse().getNameHouse();
         String usuario = purchase.getUser().getName()+ " " + purchase.getUser().getLastName();
         String message = usuario + " ha realizado una compra en nuestra página \n" + "Destino: " + destino + "\nHotel: " + hotel + "\nCoste total: " + precio;
         purchaseService.sendEmail("idealtripdaw@gmail.com", to,message);
         return "index";
     }
 }
 //