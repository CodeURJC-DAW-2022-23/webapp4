package com.idealtrip.idealTrip.controller.rest;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idealtrip.idealTrip.model.User;
import com.idealtrip.idealTrip.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/me")
	public ResponseEntity<User> me(HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
		
		if(principal != null) {
			return ResponseEntity.ok(userRepository.findByEmail(principal.getName()).orElseThrow());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}