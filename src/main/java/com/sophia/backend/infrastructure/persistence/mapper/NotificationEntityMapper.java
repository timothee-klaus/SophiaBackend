package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.domain.model.Notification;
import com.sophia.backend.infrastructure.persistence.entity.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationEntityMapper {
    public Notification toDomain(NotificationEntity e) {
        if (e == null) return null;
        Notification d = new Notification();
        d.setId(e.getId());
        d.setUuid(e.getUuid());
        d.setType(e.getType());
        d.setExpediteurUuid(e.getExpediteurUuid());
        d.setDestinataireUuid(e.getDestinataireUuid());
        d.setContenu(e.getContenu());
        d.setLu(e.isLu());
        d.setDateCreation(e.getDateCreation());
        d.setLien(e.getLien());
        d.setCreatedAt(e.getCreatedAt());
        return d;
    }

    public NotificationEntity toEntity(Notification d) {
        if (d == null) return null;
        NotificationEntity e = new NotificationEntity();
        e.setId(d.getId());
        e.setUuid(d.getUuid());
        e.setType(d.getType());
        e.setExpediteurUuid(d.getExpediteurUuid());
        e.setDestinataireUuid(d.getDestinataireUuid());
        e.setContenu(d.getContenu());
        e.setLu(d.isLu());
        e.setDateCreation(d.getDateCreation());
        e.setLien(d.getLien());
        e.setCreatedAt(d.getCreatedAt());
        return e;
    }
}

