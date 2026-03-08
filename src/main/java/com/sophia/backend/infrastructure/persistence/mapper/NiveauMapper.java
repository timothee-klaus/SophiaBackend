package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.NiveauDTO;
import com.sophia.backend.domain.model.Niveau;
import com.sophia.backend.infrastructure.persistence.entity.NiveauEntity;
import org.springframework.stereotype.Component;

@Component
public class NiveauMapper {
    public NiveauDTO toDto(Niveau n) {
        if (n == null) return null;
        NiveauDTO dto = new NiveauDTO();
        dto.setId(n.getId());
        dto.setNom(n.getNom());
        dto.setCycleId(n.getCycleId());
        dto.setEtablissementId(n.getEtablissementId());
        dto.setOrdre(n.getOrdre());
        dto.setCreatedAt(n.getCreatedAt());
        dto.setUpdatedAt(n.getUpdatedAt());
        return dto;
    }

    public Niveau toDomain(NiveauDTO d) {
        if (d == null) return null;
        Niveau n = new Niveau();
        n.setId(d.getId());
        n.setNom(d.getNom());
        n.setCycleId(d.getCycleId());
        n.setEtablissementId(d.getEtablissementId());
        n.setOrdre(d.getOrdre());
        // Ne pas mapper createdAt, updatedAt (READ_ONLY)
        return n;
    }

    public Niveau toDomain(NiveauEntity e) {
        if (e == null) return null;
        Niveau n = new Niveau();
        n.setId(e.getId());
        n.setNom(e.getNom());
        n.setCycleId(e.getCycleId());
        n.setEtablissementId(e.getEtablissementId());
        n.setOrdre(e.getOrdre());
        n.setCreatedAt(e.getCreatedAt());
        n.setUpdatedAt(e.getUpdatedAt());
        return n;
    }

    public NiveauEntity toEntity(Niveau n) {
        if (n == null) return null;
        NiveauEntity e = new NiveauEntity();
        e.setId(n.getId());
        e.setNom(n.getNom());
        e.setCycleId(n.getCycleId());
        e.setEtablissementId(n.getEtablissementId());
        e.setOrdre(n.getOrdre());
        e.setCreatedAt(n.getCreatedAt());
        e.setUpdatedAt(n.getUpdatedAt());
        return e;
    }
}
