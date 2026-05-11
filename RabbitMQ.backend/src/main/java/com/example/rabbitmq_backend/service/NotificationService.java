package com.example.rabbitmq_backend.service;

import com.example.rabbitmq_backend.model.dto.NotificationDto;
import com.example.rabbitmq_backend.messaging.consumer.MessagePublisher;
import com.example.rabbitmq_backend.model.entity.Notification;
import com.example.rabbitmq_backend.repository.NotificationRepository;
import org.apache.tomcat.util.modeler.NotificationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class NotificationService implements MessagePublisher {

    @Autowired
    private NotificationRepository notificationRepository;

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    // Thread-safe list for storing notifications
    private final List<NotificationDto> notifications = new CopyOnWriteArrayList<>();

    @Override
    public void saveNotification(NotificationDto dto) {
        if (dto != null){
            Notification notification = new Notification();
            notification.setTitle(dto.getTitle());
            notification.setMessage(dto.getMessage());
            notification.setPriority(dto.getPriority());
            notification.setStatus(dto.getStatus());

            notificationRepository.save(notification);
            logger.info("Notification saved: {} - {}", dto.getTitle(), dto.getMessage());
        }
        else {
            logger.warn("Attempted to save null notifications");
        }
    }

    @Override
    public List<NotificationDto> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationDto> getNotificationsByPriority(String priority) {
        if (priority == null || priority.isEmpty()) {
            logger.warn("Priority filter is null or empty");
            return List.of();
        }

        return notificationRepository.findByPriority(priority).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public long getNotificationCount() {
        // TODO: Return count
        return notificationRepository.count();    }

    @Override
    public void clearNotifications() {
        // TODO: Clear the list
        notificationRepository.deleteAll();
        logger.info("All notifications cleared");
    }

    private NotificationDto convertToDto(Notification notification) {
        NotificationDto dto = new NotificationDto();
        dto.setId(notification.getId().toString());
        dto.setTitle(notification.getTitle());
        dto.setMessage(notification.getMessage());
        dto.setPriority(notification.getPriority());
        dto.setStatus(notification.getStatus());
        dto.setTimestamp(notification.getTimestamp());
        return dto;
    }
}