package com.idealtrip.idealTrip.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.AbstractFileResolvingResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.idealtrip.idealTrip.model.User;
import com.idealtrip.idealTrip.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService users;

	@Autowired
	private PasswordEncoder passwordEncoder;

	User currentUser;

	@ModelAttribute
	public void addAttribute(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			users.findByEmail(principal.getName()).ifPresent(us -> currentUser = us);
			model.addAttribute("logged", true);
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("email", currentUser.getEmail());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
		} else {
			model.addAttribute("logged", false);
		}
	}

	@GetMapping("/login")
	public String login(Model model, @RequestParam Optional<String> error) {
		error.ifPresent(e -> model.addAttribute("error", true));
		return "login";
	}

	@GetMapping("/loginerror")
	public String loginerror() {
		return "loginerror";
	}

	@GetMapping("/register")
	public String showRegister(){
		return "register";
	}

	@PostMapping("/register")
	public String register(Model model, User user) throws IOException {
		if(!users.existEmail(user.getEmail())){
			user.setName(user.getName());
			user.setLastName(user.getLastName());
			// Resource image = new ClassPathResource("/static/assets/images/c1.jpg");
			// user.setProfileAvatarFile(BlobProxy.generateProxy( image.getInputStream(),  image.contentLength()));
			user.setProfileAvatar("/static/assets/images/c1.jpg");
			user.setEmail(user.getEmail());
			user.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));
			users.save(user);
			return "redirect:/login";
		}else{
			// model.addAttribute("error", true);
			return "index";
		}
	}

}