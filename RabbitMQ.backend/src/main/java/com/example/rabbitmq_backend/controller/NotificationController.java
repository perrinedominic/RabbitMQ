package com.example.rabbitmq_backend.controller;

import com.example.rabbitmq_backend.messaging.producer.NotificationProducer;
import com.example.rabbitmq_backend.model.dto.NotificationDto;
import com.example.rabbitmq_backend.service.NotificationService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "http://localhost:5173")
public class NotificationController {

    @Autowired
    private NotificationProducer notificationProducer;

    @Autowired
    private NotificationService notificationService;

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @PostMapping("/send")
    public ResponseEntity<NotificationDto> sendNotification(@RequestBody NotificationDto notification) {

        // TODO: Set id (UUID)
        // TODO: Set timestamp
        // TODO: Set status to "pending"
        // TODO: Send via producer
        // TODO: Return ResponseEntity.ok()
    }

    @GetMapping
    public ResponseEntity<List<NotificationDto>> getAllNotifications() {
        // TODO: Get all from service
        // TODO: Return ResponseEntity.ok()

        try {
            List<NotificationDto> notificationDtos = new ArrayList<>();

            // TODO: Log to fetch from service
            notificationDtos.add(new NotificationDto());

            logger.info("Successfully fetched notifications");

            return ResponseEntity.ok(notificationDtos);
        }
        catch(Exception e) {
            logger.error("Failed to get all notifications");
            return ResponseEntity.status(HttpsStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<NotificationDto>> getByPriority(@PathVariable String priority) {
        // TODO: Get by priority from service
        // TODO: Return ResponseEntity.ok()
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        // TODO: Get count from service
        // TODO: Return ResponseEntity.ok()
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearNotifications() {
        // TODO: Clear via service
        // TODO: Return ResponseEntity.ok()
    }
}