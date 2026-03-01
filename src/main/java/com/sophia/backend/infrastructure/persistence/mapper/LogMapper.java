package com.sophia.backend.infrastructure.persistence.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.sophia.backend.application.dto.LogDTO;
import com.sophia.backend.domain.enums.ActionLog;
import com.sophia.backend.domain.model.Log;
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
}
