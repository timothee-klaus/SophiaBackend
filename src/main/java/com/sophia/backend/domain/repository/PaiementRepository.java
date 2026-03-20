package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.Paiement;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaiementRepository {
    Optional<Paiement> findByUuid(UUID uuid);
    List<Paiement> findAll();
    List<Paiement> findByInscriptionUuid(UUID inscriptionUuid);
    Paiement save(Paiement paiement);
    void deleteByUuid(UUID uuid);
}
