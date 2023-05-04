package com.idealtrip.idealTrip.service;

import java.util.Optional;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import com.idealtrip.idealTrip.model.User;
import com.idealtrip.idealTrip.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository users;

    public void save(User user) {
        this.users.save(user);
    }

    public void delete(Long id) {
        users.deleteById(id);
    }

    public Optional<User> findByEmail(String email) {
        return users.findByEmail(email);
    }

    public Optional<User> findByName(String name) {
        return users.findByName(name);
    }

    public Optional<User> findById(long id) {
        return users.findById(id);
    }

    public boolean existEmail(String email) {
        Optional<User> user = findByEmail(email);
        return user.isPresent();
    }

    public List<User> findAll() {
        return users.findAll();
    }

}


