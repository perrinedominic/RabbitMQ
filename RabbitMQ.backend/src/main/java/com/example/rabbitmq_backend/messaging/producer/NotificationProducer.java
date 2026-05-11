package com.example.rabbitmq_backend.messaging.producer;

import com.example.rabbitmq_backend.config.RabbitMQConfig;
import com.example.rabbitmq_backend.model.dto.NotificationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue-name}")
    private String queueName;

    private static final Logger logger = LoggerFactory.getLogger(NotificationProducer.class);
    private static final String TASK_QUEUE_NAME = RabbitMQConfig.QUEUE_NAME;

    public void sendNotification(NotificationDto notification){
        logger.info("Sending notification: {}", notification.getTitle());
        rabbitTemplate.convertAndSend(TASK_QUEUE_NAME, notification);
        logger.info("Notification sent successfully");
    }



}