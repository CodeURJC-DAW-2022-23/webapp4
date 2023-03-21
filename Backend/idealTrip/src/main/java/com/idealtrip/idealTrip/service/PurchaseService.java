package com.idealtrip.idealTrip.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.idealtrip.idealTrip.model.Purchase;
import com.idealtrip.idealTrip.repository.PurchaseRepository;


@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchases;
    @Autowired
    private JavaMailSender mailSender;

    public void save(Purchase purchase) {
        purchases.save(purchase);
    }

    public void deleteById(long id) {
        purchases.deleteById(id);
    }

    public Optional<Purchase> findById(long id) {
        return purchases.findById(id);
    }
    public void sendEmail(String from, String to, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setText(body);

        mailSender.send(message);
    }

}
