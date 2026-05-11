package com.example.rabbitmq_backend;

import com.example.rabbitmq_backend.config.RabbitMQConfig;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Bean
    public Queue queue() {
        return new Queue(RabbitMQConfig.QUEUE_NAME, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(RabbitMQConfig.EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(RabbitMQConfig.ROUTING_KEY);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
