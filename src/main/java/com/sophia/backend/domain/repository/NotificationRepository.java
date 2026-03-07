package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.Notification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository {
    Optional<Notification> findById(Long id);
    List<Notification> findAll();
    List<Notification> findByDestinataireId(UUID destinataireId);
    List<Notification> findUnreadByDestinataireId(UUID destinataireId);
    Notification save(Notification notification);
    void deleteById(Long id);
}

