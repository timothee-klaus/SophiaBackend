package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.BlocageDTO;
import com.sophia.backend.domain.model.Blocage;
import com.sophia.backend.domain.enums.TypeBlocage;
import com.sophia.backend.infrastructure.persistence.entity.BlocageEntity;
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

    public Blocage toDomain(BlocageEntity e) {
        if (e == null) return null;
        Blocage b = new Blocage();
        b.setId(e.getId());
        b.setInscriptionId(e.getInscriptionId());
        b.setTypeBlocage(e.getTypeBlocage());
        b.setRaison(e.getRaison());
        b.setDateDebut(e.getDateDebut());
        b.setDateFin(e.getDateFin());
        b.setEstActif(e.isEstActif());
        b.setLevePar(e.getLevePar());
        b.setDateLevee(e.getDateLevee());
        b.setCreatedAt(e.getCreatedAt());
        return b;
    }

    public BlocageEntity toEntity(Blocage b) {
        if (b == null) return null;
        BlocageEntity e = new BlocageEntity();
        e.setId(b.getId());
        e.setInscriptionId(b.getInscriptionId());
        e.setTypeBlocage(b.getTypeBlocage());
        e.setRaison(b.getRaison());
        e.setDateDebut(b.getDateDebut());
        e.setDateFin(b.getDateFin());
        e.setEstActif(b.isEstActif());
        e.setLevePar(b.getLevePar());
        e.setDateLevee(b.getDateLevee());
        e.setCreatedAt(b.getCreatedAt());
        return e;
    }
}

