package com.example.rabbitmq_backend.service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Runner implements CommandLineRunner {
    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate){
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void run(String... args) throws Exception{
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(MessagingRabbitmqApplication.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
        receiver.getLatch().await(1000, TimeUnit.MILLISECONDS);
    }
}

public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);

    @RabbitLister(queues = MessagingRabbitmqApplication.queueName)
    public void receiverMessage(String message){

    }
}
