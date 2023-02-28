 package com.idealtrip.idealTrip.controller;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.GetMapping;

 import com.idealtrip.idealTrip.service.UserService;

 @Controller
 public class UserController {

      @Autowired
      private UserService userService;

      @GetMapping("/login")
      public String login(){
          return "login";
      }
 }
