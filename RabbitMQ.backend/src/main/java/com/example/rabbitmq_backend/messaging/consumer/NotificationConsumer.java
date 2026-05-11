package com.example.rabbitmq_backend.messaging.consumer;

import com.example.rabbitmq_backend.config.RabbitMQConfig;
import com.example.rabbitmq_backend.model.dto.NotificationDto;
import com.example.rabbitmq_backend.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    @Value("${rabbitmq.queue-name}")
    private String queueName;

    @Autowired
    private NotificationService notificationService;

    private static final Logger logger = LoggerFactory.getLogger(NotificationConsumer.class);

    @RabbitListener(queues = "#{@notificationConsumer.queueName}")
    public void ReceiveNotification(NotificationDto notification) {
        logger.info("Received notification: {}", notification.getMessage());

        try {
            notificationService.saveNotification(notification);
            logger.info("Notification processed successfully");
        } catch (Exception e) {
            logger.error("Failed to process notification: {}", notification, e);
        }
    }
}