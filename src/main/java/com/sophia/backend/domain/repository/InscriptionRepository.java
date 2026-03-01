package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.Inscription;

import java.util.List;
import java.util.Optional;

public interface InscriptionRepository {
    Optional<Inscription> findById(Long id);
    List<Inscription> findAll();
    List<Inscription> findByEleveId(java.util.UUID eleveId);
    List<Inscription> findByNiveauId(Long niveauId);
    Inscription save(Inscription inscription);
    void deleteById(Long id);
}

