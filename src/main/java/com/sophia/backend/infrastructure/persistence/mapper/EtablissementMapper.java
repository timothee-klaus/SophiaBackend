package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.EtablissementDTO;
import com.sophia.backend.domain.model.Etablissement;
import com.sophia.backend.infrastructure.persistence.entity.EtablissementEntity;
import org.springframework.stereotype.Component;

@Component
public class EtablissementMapper {
    public EtablissementDTO toDto(Etablissement e) {
        if (e == null) return null;
        EtablissementDTO dto = new EtablissementDTO();
        dto.setId(e.getId());
        dto.setNom(e.getNom());
        dto.setAdresse(e.getAdresse());
        dto.setTelephone(e.getTelephone());
        dto.setEmail(e.getEmail());
        dto.setLogo(e.getLogo());
        dto.setDateCreation(e.getDateCreation());
        dto.setStatut(e.getStatut() != null ? e.getStatut().name() : null);
        dto.setCreatedAt(e.getCreatedAt());
        dto.setUpdatedAt(e.getUpdatedAt());
        return dto;
    }

    public Etablissement toDomain(EtablissementDTO d) {
        if (d == null) return null;
        Etablissement e = new Etablissement();
        e.setId(d.getId());
        e.setNom(d.getNom());
        e.setAdresse(d.getAdresse());
        e.setTelephone(d.getTelephone());
        e.setEmail(d.getEmail());
        e.setLogo(d.getLogo());
        e.setDateCreation(d.getDateCreation());
        if (d.getStatut() != null) {
            try {
                e.setStatut(com.sophia.backend.domain.enums.StatutEtablissement.valueOf(d.getStatut()));
            } catch (IllegalArgumentException ex) {}
        }
        // Ne pas mapper createdAt, updatedAt (READ_ONLY)
        return e;
    }

    public Etablissement toDomain(EtablissementEntity e) {
        if (e == null) return null;
        Etablissement etablissement = new Etablissement();
        etablissement.setId(e.getId());
        etablissement.setNom(e.getNom());
        etablissement.setAdresse(e.getAdresse());
        etablissement.setTelephone(e.getTelephone());
        etablissement.setEmail(e.getEmail());
        etablissement.setLogo(e.getLogo());
        etablissement.setDateCreation(e.getDateCreation());
        etablissement.setStatut(e.getStatut());
        etablissement.setCreatedAt(e.getCreatedAt());
        etablissement.setUpdatedAt(e.getUpdatedAt());
        return etablissement;
    }

    public EtablissementEntity toEntity(Etablissement e) {
        if (e == null) return null;
        EtablissementEntity entity = new EtablissementEntity();
        entity.setId(e.getId());
        entity.setNom(e.getNom());
        entity.setAdresse(e.getAdresse());
        entity.setTelephone(e.getTelephone());
        entity.setEmail(e.getEmail());
        entity.setLogo(e.getLogo());
        entity.setDateCreation(e.getDateCreation());
        entity.setStatut(e.getStatut());
        entity.setCreatedAt(e.getCreatedAt());
        entity.setUpdatedAt(e.getUpdatedAt());
        return entity;
    }
}
