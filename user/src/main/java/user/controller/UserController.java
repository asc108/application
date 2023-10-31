package user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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
	@GetMapping("/ok")
	public String isOK() {
		return "Ok!";
	}

}
