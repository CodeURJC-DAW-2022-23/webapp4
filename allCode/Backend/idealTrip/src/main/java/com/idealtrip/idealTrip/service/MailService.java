package com.idealtrip.idealTrip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String from, String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        System.out.println("esto es de quien es " + from);
        System.out.println("esto es para quien es " + to);
        System.out.println("esto es el asunto " + subject);
        System.out.println("esto es el texto en cuestion " + body);

        mailSender.send(message);
    }
}