package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.TranchePaiement;

import java.util.List;
import java.util.Optional;

public interface TranchePaiementRepository {
    Optional<TranchePaiement> findById(Long id);
    List<TranchePaiement> findAll();
    List<TranchePaiement> findByFraisScolaireId(Long fraisScolaireId);
    TranchePaiement save(TranchePaiement tranchePaiement);
    void deleteById(Long id);
}

