package com.idealtrip.idealTrip.service;

import com.idealtrip.idealTrip.model.Newsletter;
import com.idealtrip.idealTrip.repository.NewsletterRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    // public Optional<Newsletter> findByEmail(String email){
    //     return newsletters.findByEmail(email);
    // }
}
