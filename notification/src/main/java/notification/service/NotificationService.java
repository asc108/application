package notification.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import feignclients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import notification.model.Notification;
import notification.repository.NotificationRepository;

@Service
@RequiredArgsConstructor
public class NotificationService {
	private final NotificationRepository notificationRepository;

	public void sendNotifcation(NotificationRequest notification) {
		notificationRepository.save(Notification.builder().toUserName(notification.toUserName())
				.sender("Admin").message(notification.message()).sentAt(LocalDateTime.now()).build());
	}
	
}
