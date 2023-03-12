 package com.idealtrip.idealTrip.controller;

 import com.idealtrip.idealTrip.model.House;
 import com.idealtrip.idealTrip.model.Review;
 import com.idealtrip.idealTrip.service.HouseService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.PathVariable;

 import java.util.Optional;

 @Controller
 public class PurchaseController {
     @Autowired
     private HouseService houseService;
    @GetMapping("/purchase/{id}")
    public String purchase (Model model,@PathVariable Long id){
        Optional<House> house = houseService.findById(id);
        model.addAttribute("house", house.get());
        return "purchase";
    }
 }
 //