package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.AnneeScolaireDTO;
import com.sophia.backend.domain.model.AnneeScolaire;
import org.springframework.stereotype.Component;

@Component
public class AnneeScolaireMapper {
    public AnneeScolaireDTO toDto(AnneeScolaire a) {
        if (a == null) return null;
        return new AnneeScolaireDTO(a.getId(), a.getLibelle(), a.getDateDebut(), a.getDateFin(), a.isEstActive(), a.getCreatedAt());
    }

    public AnneeScolaire toDomain(AnneeScolaireDTO d) {
        if (d == null) return null;
        AnneeScolaire a = new AnneeScolaire();
        a.setId(d.getId());
        a.setLibelle(d.getLibelle());
        a.setDateDebut(d.getDateDebut());
        a.setDateFin(d.getDateFin());
        a.setEstActive(d.isEstActive());
        a.setCreatedAt(d.getCreatedAt());
        return a;
    }
}
