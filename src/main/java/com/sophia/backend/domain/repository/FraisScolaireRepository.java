package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.FraisScolaire;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FraisScolaireRepository {
    Optional<FraisScolaire> findByUuid(UUID uuid);
    List<FraisScolaire> findAll();
    List<FraisScolaire> findByNiveauUuid(UUID niveauUuid);
    List<FraisScolaire> findByAnneeScolaireUuid(UUID anneeScolaireUuid);
    FraisScolaire save(FraisScolaire frais);
    void deleteByUuid(UUID uuid);
}

