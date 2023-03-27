package com.idealtrip.idealTrip.controller;

import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.model.Review;
import com.idealtrip.idealTrip.model.User;
import com.idealtrip.idealTrip.service.DestinationService;
import com.idealtrip.idealTrip.service.ReviewService;
//import com.idealtrip.idealTrip.service.MailService;
import com.idealtrip.idealTrip.service.UserService;

import net.bytebuddy.asm.Advice.Return;

import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ImageBanner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdministratorController {

    @Autowired
    private UserService userService;

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private ReviewService reviewService;

    User currentUser;

    @ModelAttribute
    public void addAttribute(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();

        if (principal != null) {
            userService.findByEmail(principal.getName()).ifPresent(us -> currentUser = us);
            model.addAttribute("logged", true);
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("email", currentUser.getEmail());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
        } else {
            model.addAttribute("logged", false);
        }
    }
    // GET
    
    @GetMapping("/administrator")
    public String showWebContent(Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        model.addAttribute("reviews", reviewService.findAllReviewPage(PageRequest.of(page, size, Sort.by("id").descending())));
        model.addAttribute("destinations", destinationService.findAll());
        model.addAttribute("users", userService.findAll());
        return "administrator";
    }

    // POST

    @PostMapping("/administrator/user/{id}")
    public String editUser(@PathVariable long id, @RequestParam String userNameAdmin,
            @RequestParam String userLastNameAdmin) {
        User user = userService.findById(id).orElse(null);
        user.setName(userNameAdmin);
        user.setLastName(userLastNameAdmin);
        userService.save(user);
        return "redirect:/administrator";
    }

    @PostMapping("/administrator/addDestination")
    public String addDestination(@RequestParam String nameDestination, @RequestParam String contentDestination,
            @RequestParam Float price, @RequestParam("titleImageFile") MultipartFile file) {

        try {
            byte[] imageBytes = file.getBytes();
            Blob imageBlob = new SerialBlob(imageBytes);

            Destination destination = new Destination(nameDestination, contentDestination, price, imageBlob);

            destinationService.save(destination);

            return "redirect:/administrator";
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }

    // Delete
    
    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.delete(id);
        return "redirect:/administrator";
    }

    @RequestMapping("/deleteDestination/{id}")
    public String deleteDestination(@PathVariable long id) {
        destinationService.deleteById(id);
        return "redirect:/administrator";
    }

    @RequestMapping("/deleteReview/{id}")
    public String deleteReview(@PathVariable long id) {
        reviewService.deleteById(id);
        return "redirect:/administrator";
    }

}
