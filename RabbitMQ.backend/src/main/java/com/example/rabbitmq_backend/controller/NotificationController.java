package com.example.backend.controller;

import com.example.backend.messaging.producer.NotificationProducer;
import com.example.backend.model.dto.NotificationDto;
import com.example.backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "http://localhost:5173")
public class NotificationController {

    @Autowired
    private NotificationProducer notificationProducer;

    @Autowired
    private NotificationService notificationService;

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