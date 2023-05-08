package com.idealtrip.idealTrip.controller.rest;

import com.idealtrip.idealTrip.controller.DTOS.NewsletterDTO;
import com.idealtrip.idealTrip.service.NewsletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/newsletter")
public class newsletterRestController {

    @Autowired
    private NewsletterService mailService;
    @PostMapping("/")
    public ResponseEntity<String> sendNewsletter(@RequestBody NewsletterDTO newsletterDTO) {
        String message = "Datos de contacto: " +
                "\nemail: " + newsletterDTO.getEmail() +
                "\nAsunto: " + "subscripción confirmada" +
                "\nMensaje: " + "Usted se ha suscrito a nuestra web Idealtrip, pronto le llegarán noticioas nuestras";
        mailService.sendEmail("idealtripdaw@gmail.com", newsletterDTO.getEmail(), message);
        return ResponseEntity.ok("El correo electrónico se ha enviado correctamente.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error al enviar el correo electrónico: " + e.getMessage());
    }
}
