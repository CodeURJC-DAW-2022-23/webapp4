package com.idealtrip.idealTrip.service;

import java.util.Optional;
import java.io.IOException;
import java.util.List;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.idealtrip.idealTrip.model.User;
import com.idealtrip.idealTrip.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository users;

    @Autowired
	private PasswordEncoder passwordEncoder;

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
        Optional<User> findById = users.findById(id);
        return findById;
    }

    public boolean existEmail(String email) {
        Optional<User> user = findByEmail(email);
        return user.isPresent();
    }

    public List<User> findAll() {
        return users.findAll();
    }

    public ResponseEntity<User> register(User user) throws IOException {
		if (!this.existEmail(user.getEmail())) {
			Resource image = new ClassPathResource("/static/assets/images/c1.jpg");
			user.setProfileAvatarFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
			user.setProfileAvatar("/static/assets/images/c1.jpg");
			user.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));
			user.setRoles("USER");
			this.save(user);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}


