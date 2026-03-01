package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.FraisInscription;

import java.util.List;
import java.util.Optional;

public interface FraisInscriptionRepository {
    Optional<FraisInscription> findById(Long id);
    List<FraisInscription> findAll();
    List<FraisInscription> findByCycleId(Long cycleId);
    FraisInscription save(FraisInscription frais);
    void deleteById(Long id);
}

