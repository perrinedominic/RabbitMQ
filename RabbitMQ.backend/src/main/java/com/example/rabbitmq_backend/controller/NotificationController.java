package com.example.rabbitmq_backend.controller;

import com.example.rabbitmq_backend.messaging.producer.NotificationProducer;
import com.example.rabbitmq_backend.model.dto.NotificationDto;
import com.example.rabbitmq_backend.repository.NotificationRepository;
import com.example.rabbitmq_backend.service.NotificationService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private NotificationRepository notificationRepository;

    @PostMapping("/send")
    public ResponseEntity<NotificationDto> sendNotification(@RequestBody NotificationDto notification) {
        try {
            notificationService.saveNotification(notification);
            logger.info("Successfully sent notification.");

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (Exception e){
            logger.error("Failed to send notification: {}", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        // TODO: Set id (UUID)
        // TODO: Set timestamp
        // TODO: Set status to "pending"
        // TODO: Send via producer
        // TODO: Return ResponseEntity.ok()
    }

    @GetMapping
    public ResponseEntity<List<NotificationDto>> getAllNotifications() {
        try {
            List<NotificationDto> notificationDtos = new ArrayList<>();

            // TODO: Log to fetch from service
            notificationDtos.add(new NotificationDto());

            logger.info("Successfully fetched notifications");

            return ResponseEntity.ok(notificationDtos);
        } catch (Exception e) {
            logger.error("Failed to get all notifications");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<NotificationDto>> getByPriority(@PathVariable String priority) {
        try {
            List<NotificationDto> notifications = notificationService.getNotificationsByPriority(priority);
            logger.error("Successfully fetched notifications by priority");

            return ResponseEntity.ok(notifications);
        } catch (Exception e) {
            logger.error("Failed to get notifications by priority");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        // TODO: Get count from service
        // TODO: Return ResponseEntity.ok()
        try {
            long count = notificationService.getNotificationCount();
            logger.info("Successfully fetched notification count");

            return ResponseEntity.ok(count);
        } catch (Exception e) {
            logger.error("Failed to get notification count");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearNotifications() {
        // TODO: Clear via service
        // TODO: Return ResponseEntity.ok()
        try {
            notificationService.clearNotifications();
            logger.info("Successfully cleared all notifications");

            return ResponseEntity.ok("Successfully cleared all notifications");
        } catch (Exception e) {
            logger.error("Failed to clear all notifications");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}