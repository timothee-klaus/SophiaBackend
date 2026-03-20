package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.Notification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository {
    Optional<Notification> findById(Long id);
    Optional<Notification> findByUuid(UUID uuid);
    List<Notification> findAll();
    List<Notification> findByDestinataireUuid(UUID destinataireUuid);
    List<Notification> findByDestinataireUuidAndLuFalse(UUID destinataireUuid);
    List<Notification> findByExpediteurUuid(UUID expeditriceUuid);
    Notification save(Notification notification);
    void deleteById(Long id);
    void deleteByUuid(UUID uuid);
}

