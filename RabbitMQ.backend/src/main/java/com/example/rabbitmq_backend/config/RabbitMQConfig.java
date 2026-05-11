package com.example.rabbitmq_backend.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMQConfig {

    public static String EXCHANGE_NAME;
    public static String QUEUE_NAME;
    public static String ROUTING_KEY;

    // TODO: Define queue name as constant
    RabbitTemplate rabbitTemplate;

    public void setQueueName(String queueName){
        QUEUE_NAME = queueName;
    }

    public void setExchangeName(String exchangeName){
        EXCHANGE_NAME = exchangeName;
    }

    public void SetRoutingKey(String routingKey){
        ROUTING_KEY = routingKey;
    }

    public Queue notificationQueue(){
        rabbitTemplate.start();
        rabbitTemplate.expectedQueueNames().add(QUEUE_NAME);
        return new Queue("notifications");
    }
}