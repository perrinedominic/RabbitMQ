package com.example.rabbitmq_backend.messaging.consumer;

import com.example.rabbitmq_backend.model.dto.NotificationDto;

import java.util.List;

public interface MessagePublisher {

    void saveNotification(NotificationDto notification);

    List<NotificationDto> getAllNotifications();

    List<NotificationDto> getNotificationsByPriority(String priority);

    long getNotificationCount();

    void clearNotifications();
}
