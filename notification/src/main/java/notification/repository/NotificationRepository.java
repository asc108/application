package notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import notification.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
