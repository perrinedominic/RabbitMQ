package src.main;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.MessageProperties;

import java.util.Map;

public class Worker {

    private final static String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        // Create a new RabbitMQ connection factory with default settings
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // running locally

        // Establish a new connection using the factory
        Connection connection = factory.newConnection();

        // Create a channel for communication on this connection
        Channel channel = connection.createChannel();

        boolean durable = true;
        Map<String, Object> args = Map.of("x-queue-type", "quorum");

        // This ensures that messages are replicated across multiple nodes for fault tolerance
        channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, args);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

        // Define a callback to handle incoming messages
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            System.out.println(" [x] Received '" + message + " ");
            try {
                // Process the received message by calling doWork()
                doWork(message);
            } catch(Exception e) {
                // Log any exceptions that occur during processing
                System.out.println("Failed to receive '" + message + "' due to '" + e.getClass() + "'");
            }
                finally
            {
                // This will always execute, whether an exception occurred or not
                System.out.println(" [x] Bye");
                channel.basicAck(deliver.getEnvelope().getDeliveryTag(), false);
            }
        };

        // Start consuming messages from the "hello" queue with automatic acknowledgment disabled
        boolean autoAck = false;
        channel.basicConsume(TASK_QUEUE_NAME, autoAck, deliverCallback, consumerTag -> { });
    }

    private static void doWork(String task) throws InterruptedException {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
