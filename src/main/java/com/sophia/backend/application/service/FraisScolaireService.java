package com.sophia.backend.application.service;

import com.sophia.backend.domain.model.FraisScolaire;
import com.sophia.backend.domain.repository.FraisScolaireRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FraisScolaireService {
    private final FraisScolaireRepository fraisScolaireRepository;

    public FraisScolaireService(FraisScolaireRepository fraisScolaireRepository) {
        this.fraisScolaireRepository = fraisScolaireRepository;
    }

    public Optional<FraisScolaire> findById(Long id) {
        return fraisScolaireRepository.findById(id);
    }

    public List<FraisScolaire> findAll() {
        return fraisScolaireRepository.findAll();
    }

    public List<FraisScolaire> findByNiveauId(Long niveauId) {
        return fraisScolaireRepository.findByNiveauId(niveauId);
    }

    public List<FraisScolaire> findByAnneeScolaireId(Long anneeScolaireId) {
        return fraisScolaireRepository.findByAnneeScolaireId(anneeScolaireId);
    }

    public FraisScolaire create(FraisScolaire frais) {
        return fraisScolaireRepository.save(frais);
    }

    public FraisScolaire update(FraisScolaire frais) {
        return fraisScolaireRepository.save(frais);
    }

    public void delete(Long id) {
        fraisScolaireRepository.deleteById(id);
    }

    /**
     * Définir les frais de scolarité par niveau et par an
     */
    public FraisScolaire definirFraisScolaire(Long niveauId, Long anneeScolaireId, FraisScolaire fraisScolaire) {
        fraisScolaire.setNiveauId(niveauId);
        fraisScolaire.setAnneeScolaireId(anneeScolaireId);
        return this.create(fraisScolaire);
    }

    /**
     * Obtenir les frais de scolarité pour un niveau et une année donnée
     */
    public Optional<FraisScolaire> obtenirFraisPourNiveauEtAnnee(Long niveauId, Long anneeScolaireId) {
        return this.findByNiveauId(niveauId).stream()
                .filter(f -> f.getAnneeScolaireId().equals(anneeScolaireId))
                .findFirst();
    }
}

