package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.BlocageDTO;
import com.sophia.backend.domain.model.Blocage;
import org.springframework.stereotype.Component;

@Component
public class BlocageMapper {
    public BlocageDTO toDto(Blocage b) {
        if (b == null) return null;
        return new BlocageDTO(b.getId(), b.getInscriptionId(), b.getTypeBlocage() != null ? b.getTypeBlocage().name() : null, b.getRaison(), b.getDateDebut(), b.getDateFin(), b.isEstActif(), b.getLevePar(), b.getDateLevee(), b.getCreatedAt());
    }

    public Blocage toDomain(BlocageDTO d) {
        if (d == null) return null;
        Blocage b = new Blocage();
        b.setId(d.getId());
        b.setInscriptionId(d.getInscriptionId());
        b.setRaison(d.getRaison());
        b.setDateDebut(d.getDateDebut());
        b.setDateFin(d.getDateFin());
        b.setEstActif(d.isEstActif());
        b.setLevePar(d.getLevePar());
        b.setDateLevee(d.getDateLevee());
        b.setCreatedAt(d.getCreatedAt());
        return b;
    }
}
