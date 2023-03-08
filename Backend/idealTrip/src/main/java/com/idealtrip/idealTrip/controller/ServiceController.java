package com.idealtrip.idealTrip.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.model.User;
import com.idealtrip.idealTrip.repository.DestinationRepository;
import com.idealtrip.idealTrip.service.DestinationService;
import com.idealtrip.idealTrip.service.UserService;

// import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
// @RequestMapping("/services")
public class ServiceController {
    @Autowired
    private DestinationService destinationService;

    @Autowired 
    private UserService userService;

    // @Autowired
    // private DestinationRepository destinationRepository;
    User currentUser;


    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();

        if (principal != null){
            userService.findByEmail(principal.getName()).ifPresent(us->currentUser=us);
            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
            model.addAttribute("curretUser", currentUser);
            
        }else{
            model.addAttribute("logged", false);
        }
    }   

//     @GetMapping("/services")
//     public String listarNombresDestinos(Model model){
//         Page<Destination> destination = destinationRepository.findAll(PageRequest.of(0,3));
//         model.addAttribute("services", destination.getContent());
//         return "services";
//    }
    @GetMapping("/services")
    public String showServices(Model model){
        model.addAttribute("services", destinationService.findAll());
        return "services";
    }

   @GetMapping("/services/{id}")
   public String showDestination(Model model, @PathVariable Long id){
        Optional<Destination> destination = destinationService.findById(id);
        if (destination.isPresent()){
            model.addAttribute("service", destination);
            return "information";
        }else{
            return "services";
        }        
   }

   

    
}
