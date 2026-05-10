package com.example.backend.service;

import com.example.backend.model.dto.NotificationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    // TODO: Create in-memory storage (List<NotificationDto>)
    // Hint: Use CopyOnWriteArrayList for thread safety

    public void saveNotification(NotificationDto notification) {
        // TODO: Add to list
    }

    public List<NotificationDto> getAllNotifications() {
        // TODO: Return all notifications
    }

    public List<NotificationDto> getNotificationsByPriority(String priority) {
        // TODO: Filter and return by priority
    }

    public long getNotificationCount() {
        // TODO: Return count
    }

    public void clearNotifications() {
        // TODO: Clear the list
    }
}