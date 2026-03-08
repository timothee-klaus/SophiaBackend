package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.RecuDTO;
import com.sophia.backend.domain.model.Recu;
import com.sophia.backend.domain.enums.RecuStatut;
import com.sophia.backend.infrastructure.persistence.entity.RecuEntity;
import org.springframework.stereotype.Component;

@Component
public class RecuMapper {
    public RecuDTO toDto(Recu r) {
        if (r == null) return null;
        RecuDTO dto = new RecuDTO();
        dto.setId(r.getId());
        dto.setPaiementId(r.getPaiementId());
        dto.setDemandePar(r.getDemandePar());
        dto.setDateDemande(r.getDateDemande());
        dto.setTraitePar(r.getTraitePar());
        dto.setDateTraitement(r.getDateTraitement());
        dto.setStatut(r.getStatut() != null ? r.getStatut().name() : null);
        dto.setCheminFichier(r.getCheminFichier());
        dto.setCreatedAt(r.getCreatedAt());
        dto.setUpdatedAt(r.getUpdatedAt());
        return dto;
    }

    public Recu toDomain(RecuDTO d) {
        if (d == null) return null;
        Recu r = new Recu();
        r.setId(d.getId());
        r.setPaiementId(d.getPaiementId());
        r.setDemandePar(d.getDemandePar());
        r.setDateDemande(d.getDateDemande());
        r.setTraitePar(d.getTraitePar());
        r.setDateTraitement(d.getDateTraitement());
        r.setCheminFichier(d.getCheminFichier());
        if (d.getStatut() != null) {
            try {
                r.setStatut(RecuStatut.valueOf(d.getStatut()));
            } catch (IllegalArgumentException ex) {}
        }
        // Ne pas mapper createdAt, updatedAt (READ_ONLY)
        return r;
    }

    public Recu toDomain(RecuEntity e) {
        if (e == null) return null;
        Recu r = new Recu();
        r.setId(e.getId());
        r.setPaiementId(e.getPaiementId());
        r.setDemandePar(e.getDemandePar());
        r.setDateDemande(e.getDateDemande());
        r.setTraitePar(e.getTraitePar());
        r.setDateTraitement(e.getDateTraitement());
        r.setStatut(e.getStatut());
        r.setCheminFichier(e.getCheminFichier());
        r.setCreatedAt(e.getCreatedAt());
        r.setUpdatedAt(e.getUpdatedAt());
        return r;
    }

    public RecuEntity toEntity(Recu r) {
        if (r == null) return null;
        RecuEntity e = new RecuEntity();
        e.setId(r.getId());
        e.setPaiementId(r.getPaiementId());
        e.setDemandePar(r.getDemandePar());
        e.setDateDemande(r.getDateDemande());
        e.setTraitePar(r.getTraitePar());
        e.setDateTraitement(r.getDateTraitement());
        e.setStatut(r.getStatut());
        e.setCheminFichier(r.getCheminFichier());
        e.setCreatedAt(r.getCreatedAt());
        e.setUpdatedAt(r.getUpdatedAt());
        return e;
    }
}

