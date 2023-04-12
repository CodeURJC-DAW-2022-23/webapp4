package com.idealtrip.idealTrip.controller.rest;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.idealtrip.idealTrip.controller.DTOS.PurchaseDTO;
import com.idealtrip.idealTrip.model.House;
import com.idealtrip.idealTrip.model.Purchase;
import com.idealtrip.idealTrip.model.User;
import com.idealtrip.idealTrip.service.HouseService;
import com.idealtrip.idealTrip.service.PurchaseService;
import com.idealtrip.idealTrip.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseRestController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private UserService userService;

    User currentUser;

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();

        if (principal != null) {
            userService.findByEmail(principal.getName()).ifPresent(us -> currentUser = us);
            model.addAttribute("curretUser", currentUser);

        } else {
            model.addAttribute("logged", false);
        }
    }

    @Operation(summary = "Add a purchase")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Purchase added", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Purchase.class)) }),
            @ApiResponse(responseCode = "404", description = "House not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden or don't have permissions", content = @Content) })
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> addPurchase(@PathVariable Long id) {
        Optional<House> currentHouse = houseService.findById(id);
        if (currentHouse.isPresent()) {
            Purchase purchase = new Purchase(currentUser, currentHouse.get());
            purchaseService.save(purchase);
            PurchaseDTO responseDTO = new PurchaseDTO(purchase.getId(), currentUser.getName(),
                    currentHouse.get().getNameHouse());
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } else {
            String errorMessage = "No se encontró una casa con el ID " + id;
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }
}