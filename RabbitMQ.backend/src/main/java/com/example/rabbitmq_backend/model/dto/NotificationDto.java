package com.example.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsContructor;

import java.time.LocalDataTime;

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
}