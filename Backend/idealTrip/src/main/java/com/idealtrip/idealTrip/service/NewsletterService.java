package com.idealtrip.idealTrip.service;

import com.idealtrip.idealTrip.model.Newsletter;
import com.idealtrip.idealTrip.repository.NewsletterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewsletterService {

    @Autowired
    private NewsletterRepository newsletters;

    public void save (Newsletter newsletter){
        this.newsletters.save(newsletter);
    }

    public void delete(Long id){
        newsletters.deleteById(id);
    }

    public Optional<Newsletter> findById(Long id) {
        return newsletters.findById(id);
    }

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String from, String to, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setText(body);

        mailSender.send(message);
    }
    // public Optional<Newsletter> findByEmail(String email){
    //     return newsletters.findByEmail(email);
    // }
}
