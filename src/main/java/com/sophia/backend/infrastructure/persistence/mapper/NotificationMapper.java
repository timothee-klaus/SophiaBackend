package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.NotificationDTO;
import com.sophia.backend.domain.model.Notification;
import com.sophia.backend.domain.enums.TypeNotification;
import com.sophia.backend.infrastructure.persistence.entity.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    public NotificationDTO toDto(Notification n) {
        if (n == null) return null;
        return new NotificationDTO(n.getId(), n.getType() != null ? n.getType().name() : null, n.getExpediteurId(), n.getDestinataireId(), n.getContenu(), n.isLu(), n.getDateCreation(), n.getLien(), n.getCreatedAt());
    }

    public Notification toDomain(NotificationDTO d) {
        if (d == null) return null;
        Notification n = new Notification();
        n.setId(d.getId());
        n.setExpediteurId(d.getExpediteurId());
        n.setDestinataireId(d.getDestinataireId());
        n.setContenu(d.getContenu());
        n.setLu(d.isLu());
        n.setDateCreation(d.getDateCreation());
        n.setLien(d.getLien());
        n.setCreatedAt(d.getCreatedAt());
        return n;
    }

    public Notification toDomain(NotificationEntity e) {
        if (e == null) return null;
        Notification n = new Notification();
        n.setId(e.getId());
        n.setType(e.getType());
        n.setExpediteurId(e.getExpediteurId());
        n.setDestinataireId(e.getDestinataireId());
        n.setContenu(e.getContenu());
        n.setLu(e.isLu());
        n.setDateCreation(e.getDateCreation());
        n.setLien(e.getLien());
        n.setCreatedAt(e.getCreatedAt());
        return n;
    }

    public NotificationEntity toEntity(Notification n) {
        if (n == null) return null;
        NotificationEntity e = new NotificationEntity();
        e.setId(n.getId());
        e.setType(n.getType());
        e.setExpediteurId(n.getExpediteurId());
        e.setDestinataireId(n.getDestinataireId());
        e.setContenu(n.getContenu());
        e.setLu(n.isLu());
        e.setDateCreation(n.getDateCreation());
        e.setLien(n.getLien());
        e.setCreatedAt(n.getCreatedAt());
        return e;
    }
}

