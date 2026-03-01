package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.RecuDTO;
import com.sophia.backend.domain.model.Recu;
import org.springframework.stereotype.Component;

@Component
public class RecuMapper {
    public RecuDTO toDto(Recu r) {
        if (r == null) return null;
        return new RecuDTO(r.getId(), r.getPaiementId(), r.getDemandePar(), r.getDateDemande(), r.getTraitePar(), r.getDateTraitement(), r.getStatut() != null ? r.getStatut().name() : null, r.getCheminFichier(), r.getCreatedAt());
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
        r.setCreatedAt(d.getCreatedAt());
        return r;
    }
}
