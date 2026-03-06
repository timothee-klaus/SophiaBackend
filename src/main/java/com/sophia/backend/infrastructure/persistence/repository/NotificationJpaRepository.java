package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationJpaRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> findByDestinataireId(UUID destinataireId);
    List<NotificationEntity> findByExpediteursId(UUID expeditriceId);
}

