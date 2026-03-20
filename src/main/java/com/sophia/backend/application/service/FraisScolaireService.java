package com.sophia.backend.application.service;

import com.sophia.backend.domain.model.FraisScolaire;
import com.sophia.backend.domain.repository.FraisScolaireRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class FraisScolaireService {
    private final FraisScolaireRepository fraisScolaireRepository;

    public FraisScolaireService(FraisScolaireRepository fraisScolaireRepository) {
        this.fraisScolaireRepository = fraisScolaireRepository;
    }

    public Optional<FraisScolaire> findByUuid(java.util.UUID uuid) {
        return fraisScolaireRepository.findByUuid(uuid);
    }

    public List<FraisScolaire> findAll() {
        return fraisScolaireRepository.findAll();
    }

    public List<FraisScolaire> findByNiveauUuid(java.util.UUID niveauUuid) {
        return fraisScolaireRepository.findByNiveauUuid(niveauUuid);
    }

    public List<FraisScolaire> findByAnneeScolaireUuid(java.util.UUID anneeScolaireUuid) {
        return fraisScolaireRepository.findByAnneeScolaireUuid(anneeScolaireUuid);
    }

    public FraisScolaire create(FraisScolaire frais) {
        if (frais.getUuid() == null) {
            frais.setUuid(java.util.UUID.randomUUID());
        }
        LocalDateTime now = LocalDateTime.now();
        if (frais.getCreatedAt() == null) {
            frais.setCreatedAt(now);
        }
        frais.setUpdatedAt(now);
        return fraisScolaireRepository.save(frais);
    }

    public FraisScolaire update(java.util.UUID uuid, FraisScolaire frais) {
        if (!uuid.equals(frais.getUuid())) {
            // Optional: check if exists or set uuid
            frais.setUuid(uuid);
        }
        // préserver createdAt si déjà stocké
        fraisScolaireRepository.findByUuid(uuid)
                .map(FraisScolaire::getCreatedAt)
                .ifPresent(frais::setCreatedAt);
        frais.setUpdatedAt(LocalDateTime.now());
        return fraisScolaireRepository.save(frais);
    }

    public void deleteByUuid(java.util.UUID uuid) {
        fraisScolaireRepository.deleteByUuid(uuid);
    }

    /**
     * Définir les frais de scolarité par niveau et par an
     */
    public FraisScolaire definirFraisScolaire(java.util.UUID niveauId, java.util.UUID anneeScolaireId, FraisScolaire fraisScolaire) {
        fraisScolaire.setNiveauUuid(niveauId);
        fraisScolaire.setAnneeScolaireUuid(anneeScolaireId);
        return this.create(fraisScolaire);
    }

    /**
     * Obtenir les frais de scolarité pour un niveau et une année donnée
     */
    public Optional<FraisScolaire> obtenirFraisPourNiveauEtAnnee(java.util.UUID niveauId, java.util.UUID anneeScolaireId) {
        return this.findByNiveauUuid(niveauId).stream()
                .filter(f -> f.getAnneeScolaireUuid().equals(anneeScolaireId))
                .findFirst();
    }
}
