package com.example.rabbitmq_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {

    private String id;
    private String title;
    private String message;
    private String priority; // "high", "medium", "low"
    private LocalDateTime timestamp;
    private String status; // "pending", "processing", "delivered"

    // Custom constructor with auto-generated ID and timestamp
    public NotificationDto(String title, String message, String priority, String status) {
        this.id = UUID.randomUUID().toString();  // Convert UUID to String
        this.title = title;
        this.message = message;
        this.priority = priority;
        this.timestamp = LocalDateTime.now();
        this.status = status;
    }
}