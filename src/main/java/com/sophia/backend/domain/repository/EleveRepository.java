package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.Eleve;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EleveRepository {
    Optional<Eleve> findById(UUID id);
    Optional<Eleve> findByMatricule(String matricule);
    List<Eleve> findAll();
    List<Eleve> findByEtablissementId(Long etablissementId);
    Eleve save(Eleve eleve);
    void deleteById(UUID id);
}

