package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.Paiement;

import java.util.List;
import java.util.Optional;

public interface PaiementRepository {
    Optional<Paiement> findById(Long id);
    List<Paiement> findAll();
    List<Paiement> findByInscriptionId(Long inscriptionId);
    Paiement save(Paiement paiement);
    void deleteById(Long id);
}

