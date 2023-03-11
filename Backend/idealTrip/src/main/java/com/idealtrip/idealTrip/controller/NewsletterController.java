package com.idealtrip.idealTrip.controller;


// import java.net.URI;
// import java.net.http.HttpResponse.ResponseInfo;

// import org.aspectj.apache.bcel.classfile.NestHost;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.util.UriComponentsBuilder;

import com.idealtrip.idealTrip.model.Newsletter;
import com.idealtrip.idealTrip.service.NewsletterService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewsletterController {
    @Autowired
    private NewsletterService newsletterService;

    @PostMapping("/newsletter")
    public String createNewsletter (Model model, Newsletter newsletter){
        String email = newsletter.getEmail();
        newsletter.setEmail(email);
        String message = "Muchas gracias por suscirbirse \nA partir de ahora será notificado de todas nuestras ofertas \n Equipo de IdealTrip";
        newsletterService.sendEmail(email, "idealtripdaw@gmail.com", message);
        newsletterService.save(newsletter);
        
        // model.addAttribute("newsletterId", newsletter.getId());
        return "index";
    }
}
