package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.EleveDTO;
import com.sophia.backend.domain.model.Eleve;
import com.sophia.backend.domain.enums.Sexe;
import com.sophia.backend.domain.enums.StatutDossier;
import org.springframework.stereotype.Component;

@Component
public class EleveMapper {
    public EleveDTO toDto(Eleve e) {
        if (e == null) return null;
        return new EleveDTO(
                e.getId(),
                e.getMatricule(),
                e.getNom(),
                e.getPrenom(),
                e.getDateNaissance(),
                e.getLieuNaissance(),
                e.getSexe() != null ? e.getSexe().name() : null,
                e.getNationalite(),
                e.getAdresse(),
                e.getNomTuteur(),
                e.getTelephoneTuteur(),
                e.getEmailTuteur(),
                e.getPhotoPath(),
                e.getActeNaissancePath(),
                e.getEtablissementId(),
                e.getStatutDossier() != null ? e.getStatutDossier().name() : null,
                e.getDateCreationDossier(),
                e.getCreatedAt(),
                e.getUpdatedAt()
        );
    }

    public Eleve toDomain(EleveDTO d) {
        if (d == null) return null;
        Eleve e = new Eleve();
        e.setId(d.getId());
        e.setMatricule(d.getMatricule());
        e.setNom(d.getNom());
        e.setPrenom(d.getPrenom());
        e.setDateNaissance(d.getDateNaissance());
        e.setLieuNaissance(d.getLieuNaissance());
        // convertir les enums sexe et statutDossier depuis leurs représentations String
        if (d.getSexe() != null) {
            try {
                e.setSexe(Sexe.valueOf(d.getSexe()));
            } catch (IllegalArgumentException ex) {
                // valeur invalide, laisser null
            }
        }
        if (d.getStatutDossier() != null) {
            try {
                e.setStatutDossier(StatutDossier.valueOf(d.getStatutDossier()));
            } catch (IllegalArgumentException ex) {
                // valeur invalide, laisser null
            }
        }
        e.setNationalite(d.getNationalite());
        e.setAdresse(d.getAdresse());
        e.setNomTuteur(d.getNomTuteur());
        e.setTelephoneTuteur(d.getTelephoneTuteur());
        e.setEmailTuteur(d.getEmailTuteur());
        e.setPhotoPath(d.getPhotoPath());
        e.setActeNaissancePath(d.getActeNaissancePath());
        e.setEtablissementId(d.getEtablissementId());
        e.setDateCreationDossier(d.getDateCreationDossier());
        e.setCreatedAt(d.getCreatedAt());
        e.setUpdatedAt(d.getUpdatedAt());
        return e;
    }
}
