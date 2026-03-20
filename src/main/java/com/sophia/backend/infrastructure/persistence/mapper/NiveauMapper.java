package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.NiveauDTO;
import com.sophia.backend.domain.model.Niveau;
import org.springframework.stereotype.Component;

@Component
public class NiveauMapper {

    public NiveauDTO toDto(Niveau n) {
        if (n == null) return null;
        NiveauDTO dto = new NiveauDTO();
        dto.setUuid(n.getUid());
        dto.setNom(n.getNom());
        dto.setCycleUuid(n.getCycleUuid() != null ? n.getCycleUuid() : n.getCycleUid());
        dto.setOrdre(n.getOrdre());
        dto.setCreatedAt(n.getCreatedAt());
        dto.setUpdatedAt(n.getUpdatedAt());
        return dto;
    }

    public Niveau toDomain(NiveauDTO d) {
        if (d == null) return null;
        Niveau n = new Niveau();
        n.setUid(d.getUuid());
        n.setCycleUuid(d.getCycleUuid());
        n.setNom(d.getNom());
        n.setOrdre(d.getOrdre());
        n.setCreatedAt(d.getCreatedAt());
        n.setUpdatedAt(d.getUpdatedAt());
        return n;
    }
}
