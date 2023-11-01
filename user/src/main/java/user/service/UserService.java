package user.service;

import java.util.List;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import user.controller.UserRegistrationRequest;
import user.model.Users;
import user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;

	public void registerUser(UserRegistrationRequest registration) {
		Users user = Users.builder().firstName(registration.firstname())
				.lastName(registration.lastname()).email(registration.email()).username(registration.username()).build();
		user.setPassword(new BCryptPasswordEncoder().encode(registration.password()));
		Users userCheck = userRepository.findByEmail(user.getEmail());
		if(userCheck == null) {
			userRepository.save(user);
		} else {
			throw new IllegalArgumentException("Already registered");
		}
	}

	public List<Users> getAllUsers() {
		List<Users> users = userRepository.findAll();
		return users;
	}

}
