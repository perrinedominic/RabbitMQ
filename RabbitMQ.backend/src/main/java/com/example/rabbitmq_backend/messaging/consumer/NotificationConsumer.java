package com.example.backend.messaging.consumer;

import com.example.backend.model.dto.NotificationDto;
import com.example.backend.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {
    @Autowired
    private NotificationService notificationService;

    public void ReceiveNotification(NotificationDto notification){
        // TODO: Process the notification
        // TODO: Update service
        // TODO: Save to service
        // TODO: Log it
    }
}