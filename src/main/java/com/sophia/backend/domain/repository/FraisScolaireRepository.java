package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.FraisScolaire;

import java.util.List;
import java.util.Optional;

public interface FraisScolaireRepository {
    Optional<FraisScolaire> findById(Long id);
    List<FraisScolaire> findAll();
    List<FraisScolaire> findByNiveauId(Long niveauId);
    FraisScolaire save(FraisScolaire frais);
    void deleteById(Long id);
}

