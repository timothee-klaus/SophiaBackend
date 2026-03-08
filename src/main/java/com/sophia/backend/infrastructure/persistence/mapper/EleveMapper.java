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
        EleveDTO dto = new EleveDTO();
        dto.setId(e.getId());
        dto.setMatricule(e.getMatricule());
        dto.setNom(e.getNom());
        dto.setPrenom(e.getPrenom());
        dto.setDateNaissance(e.getDateNaissance());
        dto.setLieuNaissance(e.getLieuNaissance());
        dto.setSexe(e.getSexe() != null ? e.getSexe().name() : null);
        dto.setNationalite(e.getNationalite());
        dto.setAdresse(e.getAdresse());
        dto.setNomTuteur(e.getNomTuteur());
        dto.setTelephoneTuteur(e.getTelephoneTuteur());
        dto.setEmailTuteur(e.getEmailTuteur());
        dto.setPhotoPath(e.getPhotoPath());
        dto.setActeNaissancePath(e.getActeNaissancePath());
        dto.setEtablissementId(e.getEtablissementId());
        dto.setStatutDossier(e.getStatutDossier() != null ? e.getStatutDossier().name() : null);
        dto.setDateCreationDossier(e.getDateCreationDossier());
        dto.setCreatedAt(e.getCreatedAt());
        dto.setUpdatedAt(e.getUpdatedAt());
        return dto;
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
        // Ne pas mapper createdAt, updatedAt (READ_ONLY)
        return e;
    }
}
