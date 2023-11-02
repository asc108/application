package user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public void registerUser(@Valid @RequestBody UserRegistrationRequest registration) {
		userService.registerUser(registration);
	}

	@GetMapping("/allUsers")
	public List<Users> getAllUsers() {

		return userService.getAllUsers();
	}
	
	@DeleteMapping("/{username")
	public ResponseEntity<String> removeUser(@PathVariable String username) {
		try {
			userService.removeUser(username);
			return ResponseEntity.ok("Sucssessfull");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		
	}
	
	

}
