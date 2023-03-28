package com.idealtrip.idealTrip.controller.rest;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.idealtrip.idealTrip.controller.DTOS.UserDTO;
import com.idealtrip.idealTrip.model.User;
import com.idealtrip.idealTrip.repository.UserRepository;
import com.idealtrip.idealTrip.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/me")
	public ResponseEntity<User> me(HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
		
		if(principal != null) {
			return ResponseEntity.ok(userRepository.findByEmail(principal.getName()).orElseThrow());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> register(@RequestBody UserDTO userDTO) throws IOException {
		User user = new User(userDTO);

		if (!userService.existEmail(user.getEmail())) {
            user.setProfileAvatar("/static/assets/images/c1.jpg");
			try {
				Resource image = new ClassPathResource(user.getProfileAvatar());
                user.setProfileAvatarFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
		} catch (IOException e) {
				e.printStackTrace();
		}
            user.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));
            user.setRoles(Arrays.asList("USER"));

            userService.save(user);
            URI location = fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

            return ResponseEntity.created(location).body(user);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
	}
	// @PostMapping("/")
	// @ResponseStatus(HttpStatus.CREATED)
	// public User createUser()
}