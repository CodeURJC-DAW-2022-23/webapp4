package com.idealtrip.idealTrip.controller;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

//import com.idealtrip.idealTrip.model.Review;
import com.idealtrip.idealTrip.model.User;
//import com.idealtrip.idealTrip.repository.ReviewRepository;
//import com.idealtrip.idealTrip.service.ReviewService;
import com.idealtrip.idealTrip.service.UserService;

import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

@Controller
public class ProfileController {

	@Autowired
	private UserService users;

	// @Autowired
	// private ReviewService myRepository;

	User currentUser;
	// Pageable pageable = PageRequest.of(0, 5);
	// Page<Review> review = myRepository.findReviewByUser(currentUser, pageable);

	@ModelAttribute
	public void addAttribute(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			users.findByEmail(principal.getName()).ifPresent(us -> currentUser = us);
			model.addAttribute("logged", true);
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("email", currentUser.getEmail());
			// model.addAttribute("review", review);
			model.addAttribute("review", currentUser.getReviews());
			model.addAttribute("imageProfileFile", currentUser.getProfileAvatarFile());
			model.addAttribute("imageProfile", currentUser.getProfileAvatar());

			model.addAttribute("admin", request.isUserInRole("ADMIN"));
		} else {
			model.addAttribute("logged", false);
		}
	}

	@GetMapping("/profile/{id}")
	public String showProfiles(Model model, Long id) {
		Optional<User> user = users.findById(id);
		if (user.isPresent()) {
			model.addAttribute("profiles", user.get());
			return "profiles";
		} else {
			return "profile";
		}
	}

	@GetMapping("/profile")
	public String showProfile(Model model) {
		model.addAttribute("profile", users.findAll());
		return "profile";
	}

	
	@PostMapping("/profile")
	public String editProfile(@ModelAttribute("currentUser") User currentUser,
			@RequestParam String userName,
			@RequestParam String userLastName,
			@RequestParam("avatarFile") MultipartFile file) {

		currentUser.setName(userName);
		currentUser.setLastName(userLastName);
		try {
			currentUser.setProfileAvatarFile(new SerialBlob(file.getBytes()));
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		users.save(currentUser);
		return "redirect:/profile";
	}

	@GetMapping("/profile/avatar/{id}")
	@ResponseBody
	public byte[] getUserAvatar(@PathVariable Long id) throws SQLException {
		User user = users.findById(id).orElse(null);
		if (user != null && user.getProfileAvatarFile() != null) {
			Blob avatarBlob = user.getProfileAvatarFile();
			return avatarBlob.getBytes(1, (int) avatarBlob.length());
		}
		return null;
	}

	@PostMapping("/profileAdmin")
	public String editProfileAdmin(@ModelAttribute("currentUser") User currentUser, @RequestParam String userNameAdmin, @RequestParam String userLastNameAdmin) {
		currentUser.setName(userNameAdmin);
		currentUser.setLastName(userLastNameAdmin);
		users.save(currentUser);
		return "/profile";
	}
}
