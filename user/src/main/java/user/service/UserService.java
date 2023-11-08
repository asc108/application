package user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ampqp.RabbitMQMessageProducer;
import feignclients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import user.controller.UserRegistrationRequest;
import user.model.Users;
import user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

	private final UserRepository userRepository;
	private final RabbitMQMessageProducer r;

	public void registerUser(UserRegistrationRequest registration) {
		Users user = Users.builder().firstName(registration.firstname()).lastName(registration.lastname())
				.email(registration.email()).username(registration.username()).build();
		user.setPassword(new BCryptPasswordEncoder().encode(registration.password()));
		Optional<Users> userCheck = userRepository.findUserByUsername(user.getUsername());
		if (userCheck.isEmpty()) {
			userRepository.saveAndFlush(user);
			NotificationRequest n = new NotificationRequest(user.getUsername(), String.format("Hi %s,Welcome!",user.getFirstName()));
			r.publish(n, "internal.exchange", "internal.notification.routing-key");

		} else {
			throw new IllegalArgumentException("Already registered");
		}

	}

	public List<Users> getAllUsers() {
		List<Users> users = userRepository.findAll();
		return users;
	}

	public void removeUser(String username) {
		Optional<Users> user = userRepository.findUserByUsername(username);
		if (user.isPresent()) {
			log.info("found");
			userRepository.delete(user.get());
		} else {
			throw new IllegalArgumentException("Not found user with that username");
		}

	}

}
