package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.TranchePaiement;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TranchePaiementRepository {
    Optional<TranchePaiement> findByUuid(UUID uuid);
    List<TranchePaiement> findAll();
    List<TranchePaiement> findByFraisScolaireUuid(UUID fraisScolaireUuid);
    TranchePaiement save(TranchePaiement tranchePaiement);
    void deleteByUuid(UUID uuid);
}
