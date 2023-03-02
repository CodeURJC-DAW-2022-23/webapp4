package com.idealtrip.idealTrip.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.repository.DestinationRepository;
import com.idealtrip.idealTrip.service.DestinationService;

// import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
// @RequestMapping("/services")
public class ServiceController {
    @Autowired
    private DestinationService destinationService;

    @Autowired
    private DestinationRepository destinationRepository;
    
    // @ModelAttribute
    // public void addAttributes(Model model, HttpServletRequest request){
    //     Principal principal = request.getUserPrincipal();

    //     if (principal != null){
    //         model.addAttribute("logged", true);
    //         model.addAttribute("userName", principal.getName());
    //         model.addAttribute("admin", request.isUserInRole("ADMIN"));
    //     }else{
    //         model.addAttribute("logged", false);
    //     }
    // }   

    @GetMapping("/services")
    public String listarNombresDestinos(Model model){
        Page<Destination> destination = destinationRepository.findAll(PageRequest.of(0,3));
        model.addAttribute("services", destination.getContent());
        return "services";
   }

    
}
