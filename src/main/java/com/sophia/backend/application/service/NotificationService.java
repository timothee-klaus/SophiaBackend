package com.sophia.backend.application.service;

import com.sophia.backend.domain.enums.TypeNotification;
import com.sophia.backend.domain.model.Notification;
import com.sophia.backend.domain.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class NotificationService {
    private final NotificationRepository notificationRepository;
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }

    public Optional<Notification> findByUuid(UUID uuid) {
        return notificationRepository.findByUuid(uuid);
    }

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    public List<Notification> findByDestinataireUuid(UUID destinataireUuid) {
        return notificationRepository.findByDestinataireUuid(destinataireUuid);
    }

    public List<Notification> findNonLues(UUID destinataireUuid) {
        return notificationRepository.findByDestinataireUuidAndLuFalse(destinataireUuid);
    }

    public Notification create(Notification notification) {
        if (notification.getUuid() == null) notification.setUuid(UUID.randomUUID());
        if (notification.getType() == null) notification.setType(TypeNotification.DEMANDE_RECU);
        if (notification.getDateCreation() == null) notification.setDateCreation(LocalDateTime.now());
        if (notification.getCreatedAt() == null) notification.setCreatedAt(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    public Notification update(Notification notification) {
        if (notification.getUuid() == null) notification.setUuid(UUID.randomUUID());
        if (notification.getCreatedAt() == null) notification.setCreatedAt(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    public void delete(UUID uuid) {
        notificationRepository.deleteByUuid(uuid);
    }

    public Notification notifierReçuDisponible(String lien, UUID destinataireUuid) {
        Notification notification = new Notification();
        notification.setType(TypeNotification.RECU_DISPONIBLE);
        notification.setContenu("Un nouveau reçu est disponible");
        notification.setLien(lien);
        notification.setLu(false);
        notification.setDestinataireUuid(destinataireUuid);
        return this.create(notification);
    }

    public Notification alerterImpayesCritiques(int nombreImpayés, UUID destinataireUuid) {
        Notification notification = new Notification();
        notification.setType(TypeNotification.ALERTE_IMPAYES);
        notification.setContenu("Alerte : " + nombreImpayés + " élèves en situation d'impayés");
        notification.setLu(false);
        notification.setDestinataireUuid(destinataireUuid);
        return this.create(notification);
    }

    public Notification marquerCommeLue(UUID notificationUuid) {
        return notificationRepository.findByUuid(notificationUuid).map(notification -> {
            notification.setLu(true);
            return notificationRepository.save(notification);
        }).orElseThrow(() -> new IllegalArgumentException("Notification non trouvée"));
    }
}
