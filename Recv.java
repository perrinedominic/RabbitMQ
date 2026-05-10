import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Recv {

    private final static String QUEUE_NAME = "hello";

    /**
     * Main entry point for the RabbitMQ receiver.
     */
    public static void main(String[] argv) throws Exception {
        // Create a factory for establishing connections to RabbitMQ
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        // Establish a connection to the RabbitMQ server
        Connection connection = factory.newConnection();

        // Create a channel through which messages will be consumed
        Channel channel = connection.createChannel();

        // Declare a durable queue named "hello" with quorum type
        // This ensures the queue persists across restarts and supports high availability
        channel.queueDeclare(QUEUE_NAME, true, false, false, Map.of("x-queue-type", "quorum"));

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        // Set up a callback to handle incoming message deliveries
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };

        // Start consuming messages from the queue non-blocking
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}