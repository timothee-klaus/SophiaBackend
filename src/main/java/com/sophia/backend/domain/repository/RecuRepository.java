package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.Recu;

import java.util.List;
import java.util.Optional;

public interface RecuRepository {
    Optional<Recu> findById(Long id);
    List<Recu> findAll();
    List<Recu> findByPaiementId(Long paiementId);
    Recu save(Recu recu);
    void deleteById(Long id);
}

