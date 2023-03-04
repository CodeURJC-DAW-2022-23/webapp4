package com.idealtrip.idealTrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrivacyPolicy {

  @GetMapping("/privacyPolicy")
  public String privacyPolicy(Model model) {
    return "privacyPolicy";}
 }
