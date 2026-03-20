package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.NotificationDTO;
import com.sophia.backend.domain.model.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    public NotificationDTO toDto(Notification n) {
        if (n == null) return null;
        NotificationDTO dto = new NotificationDTO();
        dto.setUuid(n.getUuid());
        dto.setType(n.getType());
        dto.setExpediteurUuid(n.getExpediteurUuid());
        dto.setDestinataireUuid(n.getDestinataireUuid());
        dto.setContenu(n.getContenu());
        dto.setLu(n.isLu());
        dto.setDateCreation(n.getDateCreation());
        dto.setLien(n.getLien());
        dto.setCreatedAt(n.getCreatedAt());
        return dto;
    }

    public Notification toDomain(NotificationDTO d) {
        if (d == null) return null;
        Notification n = new Notification();
        n.setUuid(d.getUuid());
        n.setType(d.getType());
        n.setExpediteurUuid(d.getExpediteurUuid());
        n.setDestinataireUuid(d.getDestinataireUuid());
        n.setContenu(d.getContenu());
        n.setLu(d.isLu());
        n.setDateCreation(d.getDateCreation());
        n.setLien(d.getLien());
        n.setCreatedAt(d.getCreatedAt());
        return n;
    }
}
