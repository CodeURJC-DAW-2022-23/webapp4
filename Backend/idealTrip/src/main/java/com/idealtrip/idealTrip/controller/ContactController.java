package com.idealtrip.idealTrip.controller;

 import com.idealtrip.idealTrip.model.User;
import com.idealtrip.idealTrip.service.MailService;
import com.idealtrip.idealTrip.service.UserService;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestParam;

 @Controller
 public class ContactController {

    @Autowired
	private UserService users;

	User currentUser;

	@ModelAttribute
	public void addAttribute(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			users.findByEmail(principal.getName()).ifPresent(us -> currentUser = us);
			model.addAttribute("logged", true);
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("email", currentUser.getEmail());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
		} else {
			model.addAttribute("logged", true);
		}
	}

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