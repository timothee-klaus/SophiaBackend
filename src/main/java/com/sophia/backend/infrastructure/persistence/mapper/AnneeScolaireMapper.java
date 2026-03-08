package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.AnneeScolaireDTO;
import com.sophia.backend.domain.model.AnneeScolaire;
import com.sophia.backend.infrastructure.persistence.entity.AnneeScolaireEntity;
import org.springframework.stereotype.Component;

@Component
public class AnneeScolaireMapper {
    public AnneeScolaireDTO toDto(AnneeScolaire a) {
        if (a == null) return null;
        AnneeScolaireDTO dto = new AnneeScolaireDTO();
        dto.setId(a.getId());
        dto.setLibelle(a.getLibelle());
        dto.setDateDebut(a.getDateDebut());
        dto.setDateFin(a.getDateFin());
        dto.setEstActive(a.isEstActive());
        dto.setCreatedAt(a.getCreatedAt());
        dto.setUpdatedAt(a.getUpdatedAt());
        return dto;
    }

    public AnneeScolaire toDomain(AnneeScolaireDTO d) {
        if (d == null) return null;
        AnneeScolaire a = new AnneeScolaire();
        a.setId(d.getId());
        a.setLibelle(d.getLibelle());
        a.setDateDebut(d.getDateDebut());
        a.setDateFin(d.getDateFin());
        a.setEstActive(d.isEstActive());
        // Ne pas mapper createdAt, updatedAt (READ_ONLY)
        return a;
    }

    public AnneeScolaire toDomain(AnneeScolaireEntity e) {
        if (e == null) return null;
        AnneeScolaire a = new AnneeScolaire();
        a.setId(e.getId());
        a.setLibelle(e.getLibelle());
        a.setDateDebut(e.getDateDebut());
        a.setDateFin(e.getDateFin());
        a.setEstActive(e.isEstActive());
        a.setCreatedAt(e.getCreatedAt());
        a.setUpdatedAt(e.getUpdatedAt());
        return a;
    }

    public AnneeScolaireEntity toEntity(AnneeScolaire a) {
        if (a == null) return null;
        AnneeScolaireEntity e = new AnneeScolaireEntity();
        e.setId(a.getId());
        e.setLibelle(a.getLibelle());
        e.setDateDebut(a.getDateDebut());
        e.setDateFin(a.getDateFin());
        e.setEstActive(a.isEstActive());
        e.setCreatedAt(a.getCreatedAt());
        e.setUpdatedAt(a.getUpdatedAt());
        return e;
    }
}
