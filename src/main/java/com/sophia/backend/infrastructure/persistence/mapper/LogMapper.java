package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.LogDTO;
import com.sophia.backend.domain.model.Log;
import org.springframework.stereotype.Component;

@Component
public class LogMapper {
    public LogDTO toDto(Log l) {
        if (l == null) return null;
        LogDTO dto = new LogDTO();
        dto.setUuid(l.getUuid());
        dto.setUtilisateurUuid(l.getUtilisateurId());
        dto.setAction(l.getAction().name());
        dto.setEntite(l.getEntite());
        dto.setEntiteId(l.getEntiteId());
        dto.setAnciennesValeurs(l.getAnciennesValeurs());
        dto.setNouvellesValeurs(l.getNouvellesValeurs());
        dto.setAdresseIp(l.getAdresseIp());
        dto.setUserAgent(l.getUserAgent());
        dto.setDateAction(l.getDateAction());
        dto.setDescription(l.getDescription());
        return dto;
    }

    public Log toDomain(LogDTO d) {
        if (d == null) return null;
        Log l = new Log();
        l.setUuid(d.getUuid());
        l.setUtilisateurId(d.getUtilisateurUuid());
        l.setAction(com.sophia.backend.domain.enums.ActionLog.valueOf(d.getAction()));
        l.setEntite(d.getEntite());
        l.setEntiteId(d.getEntiteId());
        l.setAnciennesValeurs(d.getAnciennesValeurs());
        l.setNouvellesValeurs(d.getNouvellesValeurs());
        l.setAdresseIp(d.getAdresseIp());
        l.setUserAgent(d.getUserAgent());
        l.setDateAction(d.getDateAction());
        l.setDescription(d.getDescription());
        return l;
    }
}
