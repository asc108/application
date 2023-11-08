package feignclients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("notification")
public interface NotificationClient {

	@PostMapping("/api/notification")
	public void sendNotification(@RequestBody NotificationRequest notification);
}
