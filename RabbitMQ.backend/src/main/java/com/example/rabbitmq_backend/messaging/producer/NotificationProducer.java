package com.example.backend.messaging.producer;

import com.example.backend.model.dto.NoficationDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void SendNotification(NoficiationDto notification){
        // TODO: Send notification to RabbitMQ queue
        // use rabbitTemplate.convertAndSend()
    }
}