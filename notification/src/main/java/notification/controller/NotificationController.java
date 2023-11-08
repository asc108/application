package notification.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import feignclients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import notification.service.NotificationService;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {
	
	private final NotificationService notificationService;
	
	@PostMapping
	public void sendNotificationUser(@RequestBody NotificationRequest notification) {
		notificationService.sendNotifcation(notification);
	}

	
	

}
