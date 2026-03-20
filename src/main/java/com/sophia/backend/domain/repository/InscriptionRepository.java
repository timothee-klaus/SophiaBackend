package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.Inscription;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InscriptionRepository {
    Optional<Inscription> findByUuid(UUID uuid);
    List<Inscription> findAll();
    List<Inscription> findByEleveUuid(UUID eleveUuid);
    List<Inscription> findByNiveauUuid(UUID niveauUuid);
    Inscription save(Inscription inscription);
    void deleteByUuid(UUID uuid);
}

