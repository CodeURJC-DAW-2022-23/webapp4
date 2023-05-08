package com.idealtrip.idealTrip.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;

import com.idealtrip.idealTrip.controller.DTOS.ContactDTO;
import com.idealtrip.idealTrip.model.*;

import com.idealtrip.idealTrip.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/contact")
public class ContactRestController {

    @Autowired
    private MailService mailService;

    @PostMapping("/")
    public ResponseEntity<String> sendMail(@RequestBody ContactDTO contactDTO) {
        String message = "Datos de contacto:\nNombre: " + contactDTO.getName() +
                "\nemail: " + contactDTO.getEmail() +
                "\nAsunto: " + contactDTO.getSubject() +
                "\nMensaje: " + contactDTO.getMessage();
        mailService.sendEmail(contactDTO.getEmail(), contactDTO.getEmail(), contactDTO.getSubject(), message);
        return ResponseEntity.ok("El correo electrónico se ha enviado correctamente.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error al enviar el correo electrónico: " + e.getMessage());
    }
}

