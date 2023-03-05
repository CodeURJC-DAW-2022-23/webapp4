package com.idealtrip.idealTrip.controller;

 import com.idealtrip.idealTrip.service.MailService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestParam;

 @Controller
 public class ContactController {
    @Autowired
    private MailService mailService;
     @GetMapping("/contact")
     public String contact(Model model) {
         return "contact";
     }
     @PostMapping("/sendMail")
     public String sendMail(@RequestParam("w3lName") String name,@RequestParam("w3lSender") String from,  @RequestParam("w3lSubect") String subject, @RequestParam("w3lMessage") String text){

        String message = "\nDatos de contacto: " + "\nNombre: " + name + "\nemail: " + from + "\n" + text;
        mailService.sendEmail(from, "idealtripdaw@gmail.com",subject, message);
        return "index";
    }
 }