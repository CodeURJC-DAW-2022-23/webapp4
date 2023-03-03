 package com.idealtrip.idealTrip.controller;

 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.ui.Model;

 @Controller
 public class PurchaseController {
    @GetMapping("/purchase")
    public String purchase (Model model){
        return "purchase";
    }
 }
 //