package com.tota.notification;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.tota.clients.notification.NotificationRequest;

@Service
public record NotificationService(NotificationRepository notificationRepository) {
    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification.builder()
                        .toCustomerId(notificationRequest.toCustomerId())
                        .toCustomerEmail(notificationRequest.toCustomerEmail())
                        .sender("Tota")
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now())
                        .build());
    }
}
