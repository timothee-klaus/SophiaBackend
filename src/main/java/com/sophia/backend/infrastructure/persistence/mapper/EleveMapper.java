package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.EleveDTO;
import com.sophia.backend.domain.model.Eleve;
import org.springframework.stereotype.Component;

@Component
public class EleveMapper {
    public EleveDTO toDto(Eleve e) {
        if (e == null) return null;
        EleveDTO dto = new EleveDTO();
        dto.setUuid(e.getId());
        dto.setMatricule(e.getMatricule());
        dto.setNom(e.getNom());
        dto.setPrenom(e.getPrenom());
        dto.setDateNaissance(e.getDateNaissance());
        dto.setLieuNaissance(e.getLieuNaissance());
        dto.setSexe(e.getSexe());
        dto.setNationalite(e.getNationalite());
        dto.setAdresse(e.getAdresse());
        dto.setNomTuteur(e.getNomTuteur());
        dto.setTelephoneTuteur(e.getTelephoneTuteur());
        dto.setEmailTuteur(e.getEmailTuteur());
        dto.setPhotoFournie(e.isPhotoFournie());
        dto.setActeNaissanceFourni(e.isActeNaissanceFourni());
        dto.setCertificatResidenceFourni(e.isCertificatResidenceFourni());
        dto.setBulletinsFournis(e.isBulletinsFournis());
        dto.setStatutDossier(e.getStatutDossier());
        dto.setDateCreationDossier(e.getDateCreationDossier());
        dto.setCreatedAt(e.getCreatedAt());
        dto.setUpdatedAt(e.getUpdatedAt());
        return dto;
    }

    public Eleve toDomain(EleveDTO d) {
        if (d == null) return null;
        Eleve e = new Eleve();
        e.setId(d.getUuid());
        e.setMatricule(d.getMatricule());
        e.setNom(d.getNom());
        e.setPrenom(d.getPrenom());
        e.setDateNaissance(d.getDateNaissance());
        e.setLieuNaissance(d.getLieuNaissance());
        e.setSexe(d.getSexe());
        e.setStatutDossier(d.getStatutDossier());
        e.setNationalite(d.getNationalite());
        e.setAdresse(d.getAdresse());
        e.setNomTuteur(d.getNomTuteur());
        e.setTelephoneTuteur(d.getTelephoneTuteur());
        e.setEmailTuteur(d.getEmailTuteur());
        e.setPhotoFournie(d.isPhotoFournie());
        e.setActeNaissanceFourni(d.isActeNaissanceFourni());
        e.setCertificatResidenceFourni(d.isCertificatResidenceFourni());
        e.setBulletinsFournis(d.isBulletinsFournis());
        e.setDateCreationDossier(d.getDateCreationDossier());
        e.setCreatedAt(d.getCreatedAt());
        e.setUpdatedAt(d.getUpdatedAt());
        return e;
    }
}
