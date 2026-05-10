package com.example.backend.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // TODO: Define queue name as constant

    public Queue notificationQueue(){
        return new Queue("notifications");
    }
}