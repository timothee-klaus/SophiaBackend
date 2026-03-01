package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.Niveau;

import java.util.List;
import java.util.Optional;

public interface NiveauRepository {
    Optional<Niveau> findById(Long id);
    List<Niveau> findAll();
    List<Niveau> findByCycleId(Long cycleId);
    List<Niveau> findByEtablissementId(Long etablissementId);
    Niveau save(Niveau niveau);
    void deleteById(Long id);
}

