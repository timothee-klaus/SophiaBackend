package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.InscriptionDTO;
import com.sophia.backend.domain.model.Inscription;
import org.springframework.stereotype.Component;

@Component
public class InscriptionMapper {
    public InscriptionDTO toDto(Inscription i) {
        if (i == null) return null;
        return new InscriptionDTO(
                i.getId(),
                i.getEleveId(),
                i.getNiveauId(),
                i.getAnneeScolaireId(),
                i.getDateInscription(),
                i.getStatut() != null ? i.getStatut().name() : null,
                i.getCommentaire(),
                i.getCreatedAt(),
                i.getUpdatedAt()
        );
    }

    public Inscription toDomain(InscriptionDTO d) {
        if (d == null) return null;
        Inscription i = new Inscription();
        i.setId(d.getId());
        i.setEleveId(d.getEleveId());
        i.setNiveauId(d.getNiveauId());
        i.setAnneeScolaireId(d.getAnneeScolaireId());
        i.setDateInscription(d.getDateInscription());
        i.setCommentaire(d.getCommentaire());
        i.setCreatedAt(d.getCreatedAt());
        i.setUpdatedAt(d.getUpdatedAt());
        return i;
    }
}
