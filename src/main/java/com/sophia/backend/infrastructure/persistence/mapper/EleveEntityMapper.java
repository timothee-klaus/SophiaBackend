package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.domain.model.Eleve;
import com.sophia.backend.infrastructure.persistence.entity.EleveEntity;
import org.springframework.stereotype.Component;

@Component
public class EleveEntityMapper {
    public Eleve toDomain(EleveEntity e) {
        if (e == null) return null;
        Eleve d = new Eleve();
        d.setId(e.getId());
        d.setMatricule(e.getMatricule());
        d.setNom(e.getNom());
        d.setPrenom(e.getPrenom());
        d.setDateNaissance(e.getDateNaissance());
        d.setLieuNaissance(e.getLieuNaissance());
        d.setSexe(e.getSexe());
        d.setNationalite(e.getNationalite());
        d.setAdresse(e.getAdresse());
        d.setNomTuteur(e.getNomTuteur());
        d.setTelephoneTuteur(e.getTelephoneTuteur());
        d.setEmailTuteur(e.getEmailTuteur());
        d.setPhotoPath(e.getPhotoPath());
        d.setActeNaissancePath(e.getActeNaissancePath());
        d.setEtablissementId(e.getEtablissementId());
        d.setStatutDossier(e.getStatutDossier());
        d.setDateCreationDossier(e.getDateCreationDossier());
        d.setCreatedAt(e.getCreatedAt());
        d.setUpdatedAt(e.getUpdatedAt());
        return d;
    }

    public EleveEntity toEntity(Eleve d) {
        if (d == null) return null;
        EleveEntity e = new EleveEntity();
        e.setId(d.getId());
        e.setMatricule(d.getMatricule());
        e.setNom(d.getNom());
        e.setPrenom(d.getPrenom());
        e.setDateNaissance(d.getDateNaissance());
        e.setLieuNaissance(d.getLieuNaissance());
        e.setSexe(d.getSexe());
        e.setNationalite(d.getNationalite());
        e.setAdresse(d.getAdresse());
        e.setNomTuteur(d.getNomTuteur());
        e.setTelephoneTuteur(d.getTelephoneTuteur());
        e.setEmailTuteur(d.getEmailTuteur());
        e.setPhotoPath(d.getPhotoPath());
        e.setActeNaissancePath(d.getActeNaissancePath());
        e.setEtablissementId(d.getEtablissementId());
        e.setStatutDossier(d.getStatutDossier());
        e.setDateCreationDossier(d.getDateCreationDossier());
        e.setCreatedAt(d.getCreatedAt());
        e.setUpdatedAt(d.getUpdatedAt());
        return e;
    }
}
