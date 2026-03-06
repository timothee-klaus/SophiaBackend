package com.sophia.backend.infrastructure.persistence.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.sophia.backend.application.dto.LogDTO;
import com.sophia.backend.domain.enums.ActionLog;
import com.sophia.backend.domain.model.Log;
import com.sophia.backend.infrastructure.persistence.entity.LogEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class LogMapper {
    public LogDTO toDto(Log l) {
        if (l == null) return null;
        return new LogDTO(l.getId(), l.getUtilisateurId(), l.getAction() != null ? l.getAction().name() : null, l.getEntite(), l.getEntiteId(), l.getAnciennesValeurs(), l.getNouvellesValeurs(), l.getIpAdresse(), l.getUserAgent(), l.getDateAction(), l.getDescription());
    }

    public Log toDomain(LogDTO d) {
        if (d == null) return null;
        Log l = new Log();
        l.setId(d.getId());
        l.setUtilisateurId(d.getUtilisateurId());
        if (d.getAction() != null) {
            try { l.setAction(ActionLog.valueOf(d.getAction())); } catch (IllegalArgumentException ex) {}
        }
        l.setEntite(d.getEntite());
        l.setEntiteId(d.getEntiteId());
        l.setAnciennesValeurs(d.getAnciennesValeurs());
        l.setNouvellesValeurs(d.getNouvellesValeurs());
        l.setIpAdresse(d.getIpAdresse());
        l.setUserAgent(d.getUserAgent());
        l.setDateAction(d.getDateAction());
        l.setDescription(d.getDescription());
        return l;
    }

    public Log toDomain(LogEntity e) {
        if (e == null) return null;
        Log l = new Log();
        l.setId(e.getId());
        l.setUtilisateurId(e.getUtilisateurId());
        l.setAction(e.getAction());
        l.setEntite(e.getEntite());
        l.setEntiteId(e.getEntiteId());
        l.setAnciennesValeurs(e.getAnciennesValeurs());
        l.setNouvellesValeurs(e.getNouvellesValeurs());
        l.setIpAdresse(e.getIpAdresse());
        l.setUserAgent(e.getUserAgent());
        l.setDateAction(e.getDateAction());
        l.setDescription(e.getDescription());
        return l;
    }

    public LogEntity toEntity(Log l) {
        if (l == null) return null;
        LogEntity e = new LogEntity();
        e.setId(l.getId());
        e.setUtilisateurId(l.getUtilisateurId());
        e.setAction(l.getAction());
        e.setEntite(l.getEntite());
        e.setEntiteId(l.getEntiteId());
        e.setAnciennesValeurs(l.getAnciennesValeurs());
        e.setNouvellesValeurs(l.getNouvellesValeurs());
        e.setIpAdresse(l.getIpAdresse());
        e.setUserAgent(l.getUserAgent());
        e.setDateAction(l.getDateAction());
        e.setDescription(l.getDescription());
        return e;
    }
}

