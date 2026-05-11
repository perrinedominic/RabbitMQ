package com.example.rabbitmq_backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Message cannot be blank")
    private String message;

    @NotBlank(message = "Message cannot be blank")
    @Pattern(regexp = "high|medium|low", message = "Priority must be high, medium, or low")
    private String priority; // "high", "medium", "low"


    private LocalDateTime timestamp;

    @NotBlank(message = "Status cannot be blank")
    @Pattern(regexp = "pending|processing|delivered", message = "Status must be pending, processing, or delivered")
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