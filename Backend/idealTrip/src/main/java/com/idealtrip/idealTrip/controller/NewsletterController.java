package com.idealtrip.idealTrip.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import com.idealtrip.idealTrip.model.Newsletter;
import com.idealtrip.idealTrip.service.NewsletterService;


@Controller
public class NewsletterController {
    @Autowired
    private NewsletterService newsletterService;

    @PostMapping("/newsletter")
    public String createNewsletter (Model model, Newsletter newsletter){
        String email = newsletter.getEmail();
        newsletter.setEmail(email);
        String message = "Muchas gracias por suscirbirse \nA partir de ahora será notificado de todas nuestras ofertas\n Equipo de IdealTrip";
        newsletterService.sendEmail( "idealtripdaw@gmail.com", email, message);
        newsletterService.save(newsletter);
        
        return "index";
    }
}
