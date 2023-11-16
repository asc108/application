package user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.status.Status;
import feignclients.user.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import user.model.Users;
import user.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationRequest registration) {
		try {
			userService.registerUser(registration);
			return ResponseEntity.status(HttpStatus.CREATED).body("Created");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already registered");
		}

	}

	@GetMapping("/allUsers")
	@PreAuthorize("hasRole('USER')")
	public List<Users> getAllUsers() {
		return userService.getAllUsers();
	}

	@DeleteMapping("/{username}")
	public ResponseEntity<String> removeUser(@PathVariable String username) {
		try {
			userService.removeUser(username);
			return ResponseEntity.ok("Sucssessfull");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

	}

	@GetMapping("/user")
	public UserResponse findUser(@RequestParam("username") String username) {
		UserResponse response = userService.findUser(username);
		return response;
	}

}
