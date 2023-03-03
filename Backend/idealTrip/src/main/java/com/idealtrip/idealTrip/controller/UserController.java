 package com.idealtrip.idealTrip.controller;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.idealtrip.idealTrip.model.User;
import com.idealtrip.idealTrip.repository.UserRepository;


 @Controller
 public class UserController {

      @Autowired
      private UserRepository userRepo;

      @PostMapping("/addUser")
      public String registerUser(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
        return "index";

        
      }
      @GetMapping("/testUser") 
        public String userList (Model model){
            List<User>  userList = userRepo.findAll();
            model.addAttribute("userList", userList);
            return "testUser";
        }
 }
