package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.NotificationDTO;
import com.sophia.backend.domain.model.Notification;
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
}
