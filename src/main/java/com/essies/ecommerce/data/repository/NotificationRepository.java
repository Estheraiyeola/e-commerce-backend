package com.essies.ecommerce.data.repository;

import com.essies.ecommerce.data.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
