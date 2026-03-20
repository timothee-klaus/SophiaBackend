package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.domain.model.Log;
import com.sophia.backend.infrastructure.persistence.entity.LogEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class LogEntityMapper {
    public Log toDomain(LogEntity e) {
        if (e == null) return null;
        Log d = new Log();
        d.setUuid(e.getUuid());
        d.setUtilisateurId(e.getUtilisateurId());
        d.setAction(e.getAction());
        d.setEntite(e.getEntite());
        d.setEntiteId(e.getEntiteId());
        d.setAnciennesValeurs(e.getAnciennesValeurs());
        d.setNouvellesValeurs(e.getNouvellesValeurs());
        d.setAdresseIp(e.getAdresseIp());
        d.setUserAgent(e.getUserAgent());
        d.setDateAction(e.getDateAction());
        d.setDescription(e.getDescription());
        return d;
    }

    public LogEntity toEntity(Log d) {
        if (d == null) return null;
        LogEntity e = new LogEntity();
        e.setUuid(d.getUuid() != null ? d.getUuid() : UUID.randomUUID());
        e.setUtilisateurId(d.getUtilisateurId());
        e.setAction(d.getAction());
        e.setEntite(d.getEntite());
        e.setEntiteId(d.getEntiteId());
        e.setAnciennesValeurs(d.getAnciennesValeurs());
        e.setNouvellesValeurs(d.getNouvellesValeurs());
        e.setAdresseIp(d.getAdresseIp());
        e.setUserAgent(d.getUserAgent());
        e.setDateAction(d.getDateAction() != null ? d.getDateAction() : LocalDateTime.now());
        e.setDescription(d.getDescription());
        return e;
    }
}

