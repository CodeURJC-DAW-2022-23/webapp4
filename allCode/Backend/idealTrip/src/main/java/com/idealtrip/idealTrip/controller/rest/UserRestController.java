package com.idealtrip.idealTrip.controller.rest;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import com.idealtrip.idealTrip.controller.DTOS.UserEditDTO;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.idealtrip.idealTrip.controller.DTOS.UserDTO;
import com.idealtrip.idealTrip.model.Purchase;
import com.idealtrip.idealTrip.model.Review;
import com.idealtrip.idealTrip.model.User;
import com.idealtrip.idealTrip.repository.UserRepository;
import com.idealtrip.idealTrip.service.PurchaseService;
import com.idealtrip.idealTrip.service.ReviewService;
import com.idealtrip.idealTrip.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private PurchaseService purchaseService;

	User currentUser;

	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			userService.findByEmail(principal.getName()).ifPresent(us -> currentUser = us);
			model.addAttribute("curretUser", currentUser);

		} else {
			model.addAttribute("logged", false);
		}
	}

	@Operation(summary = "Get information about the current user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the user", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "404", description = "User not found ", content = @Content),
			@ApiResponse(responseCode = "403", description = "Forbiden or don't have permissions", content = @Content) })
	@GetMapping("/me")
	public ResponseEntity<User> me(HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			return ResponseEntity.ok(userRepository.findByEmail(principal.getName()).orElseThrow());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Edit the current user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the user", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "404", description = "User not found ", content = @Content),
			@ApiResponse(responseCode = "403", description = "Forbiden or don't have permissions", content = @Content) })
	@PostMapping("/me/edit")
	public ResponseEntity<User> editMe(HttpServletRequest request, @RequestBody UserEditDTO userDTO) {

		Principal principal = request.getUserPrincipal();
		Optional<User> user = userService.findByEmail(principal.getName());
		User useraux = user.get();
		useraux.setEmail(userDTO.getEmail());
		useraux.setName(userDTO.getFirstName());
		useraux.setLastName(userDTO.getLastName());
		userService.save(useraux);
		return ResponseEntity.ok(user.get());
	}

	@Operation(summary = "Register a new user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "User created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbiden or don't have permissions", content = @Content) })
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

	@Operation(summary = "Update the user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Profile updated", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "404", description = "Profile not found ", content = @Content),
			@ApiResponse(responseCode = "403", description = "Forbidden or don't have permissions", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	@PostMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable long id,
			@RequestParam("name") String name,
			@RequestParam("lastName") String lastName,
			@RequestParam(value = "profileAvatarFile", required = false) MultipartFile profileAvatarFile) {
		try {
			User user = userService.findById(id).orElseThrow();
			user.setName(name);
			user.setLastName(lastName);

			if (profileAvatarFile != null) {
				byte[] imageBytes = profileAvatarFile.getBytes();
				Blob imageBlob = new SerialBlob(imageBytes);
				user.setProfileAvatarFile(imageBlob);
			}
			User updatedUser = userRepository.save(user);
			return ResponseEntity.ok(updatedUser);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating profile");
		}
	}

	@GetMapping("/profileAvatarFile/{id}")
	public ResponseEntity<Resource> downloadProfileAvatar(@PathVariable long id) throws SQLException {
		Optional<User> user = userService.findById(id);

		if (user.isPresent()) {
			Resource file = new InputStreamResource(user.get().getProfileAvatarFile().getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(user.get().getProfileAvatarFile().length()).body(file);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "user reviews")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "done", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "404", description = "review not found ", content = @Content),
			@ApiResponse(responseCode = "403", description = "Forbiden o don't have permissions", content = @Content) })
	@GetMapping("/reviews/{id}")
	@JsonView(Review.Basic.class)
	public ResponseEntity<List<Review>> userReviews(@PathVariable long id) {
		Optional<User> user = userService.findById(id);
		if (user.isPresent()) {
			List<Review> review = reviewService.findByUserId(user.get().getId());
			return ResponseEntity.ok(review);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUserData(@PathVariable("userId") Long userId) {
		Optional<User> user = userService.findById(userId);
		try {
			if (user.isPresent()) {
				List<Review> reviews = reviewService.findByUserId(userId);
				reviewService.deleteAll(reviews);

				List<Purchase> purchases = purchaseService.findByUserId(userId);
				purchaseService.deleteAll(purchases);

				userService.delete(userId);
			}
			return ResponseEntity.ok("UserData deleted for user with id: " + userId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error deleting user data: " + e.getMessage());
		}
	}

	@GetMapping("/userList")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> userList = userService.findAll();
		return ResponseEntity.ok(userList);
	}

	@GetMapping("/rol/{id}")
	public boolean checkAdmin(@PathVariable long id){
		Optional<User> user = userService.findById(id);
		int userRole = user.get().getRoles().size();
		return userRole > 1;
	} 

}
