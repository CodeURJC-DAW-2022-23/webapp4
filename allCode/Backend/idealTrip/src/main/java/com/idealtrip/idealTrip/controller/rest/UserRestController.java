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
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.idealtrip.idealTrip.controller.DTOS.UpdateUserDTO;
import com.idealtrip.idealTrip.controller.DTOS.UserDTO;
import com.idealtrip.idealTrip.model.User;
import com.idealtrip.idealTrip.repository.UserRepository;
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
	public ResponseEntity<User> editMe(HttpServletRequest request,@RequestBody UserEditDTO userDTO) {

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
            @ApiResponse(responseCode = "403", description = "Forbiden o don't have permissions", content = @Content) })
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody UpdateUserDTO updatedUser){
		Optional<User> user = userService.findById(id);
        if(user.isPresent()){
			User user2 = user.get();
			user2.setName(updatedUser.getName());
			user2.setLastName(updatedUser.getLastName());
			userService.save(user2);
			return ResponseEntity.ok(user.get());
		}else{
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/profileAvatarFile")
	public ResponseEntity<Resource> downloadProfileAvatar(@PathVariable long id) throws SQLException {
		Optional<User> user = userService.findById(id);

		if (user.isPresent() && user.get().getProfileAvatarFile()!= null) {
			Resource file = new InputStreamResource(user.get().getProfileAvatarFile().getBinaryStream());
			
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(user.get().getProfileAvatarFile().length()).body(file);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}