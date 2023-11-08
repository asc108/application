package notification.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import feignclients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import notification.service.NotificationService;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
	private final NotificationService notificationService;

	@RabbitListener(queues = "${rabbitmq.queues.notification}")
	public void consumer(NotificationRequest notificationRequest) {
		log.info("Consumed {} from queue", notificationRequest);
		notificationService.sendNotifcation(notificationRequest);
	}
}
