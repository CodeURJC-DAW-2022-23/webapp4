package com.idealtrip.idealTrip.controller;

import com.idealtrip.idealTrip.model.User;
import com.idealtrip.idealTrip.service.UserService;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AdministratorController {

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
            model.addAttribute("logged", false);
        }
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser (Model model,@PathVariable long id){
        users.delete(id);
        return "profile";
    }
}