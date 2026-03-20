package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.FraisInscription;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FraisInscriptionRepository {
    Optional<FraisInscription> findByUuid(UUID uuid);
    List<FraisInscription> findAll();
    List<FraisInscription> findByCycleUuid(UUID cycleUuid);
    List<FraisInscription> findByAnneeScolaireUuid(UUID anneeScolaireUuid);
    FraisInscription save(FraisInscription frais);
    void deleteByUuid(UUID uuid);
}
