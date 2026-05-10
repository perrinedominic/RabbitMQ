import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class NewTask {
    // Constant defining the name of the RabbitMQ queue for tasks.
    private final static String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        // Create a new instance of ConnectionFactory to manage connection details.
        ConnectionFactory factory = new ConnectionFactory();
        // Set the host address for the RabbitMQ server (default is localhost).
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            // Define queue durability to persist across restarts.
            boolean durable = true;

            // Map of additional arguments: setting up a quorum queue type for high availability.
            Map<String, String> args = Map.of("x-queue-type", "quorum");

            // Declare the task queue with the specified name and parameters.
            channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, args);

            // Construct a message string from command-line arguments.
            String message = String.join(" ", argv);

            // Publish the message to the declared RabbitMQ queue.
            channel.basicPublish("", TASK_QUEUE_NAME,
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes(StandardCharsets.UTF_8));

            // Print confirmation that the message has been published successfully.
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}