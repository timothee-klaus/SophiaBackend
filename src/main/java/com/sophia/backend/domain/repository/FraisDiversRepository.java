package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.FraisDivers;

import java.util.List;
import java.util.Optional;

public interface FraisDiversRepository {
    Optional<FraisDivers> findById(Long id);
    List<FraisDivers> findAll();
    List<FraisDivers> findByNiveauId(Long niveauId);
    List<FraisDivers> findByAnneeScolaireId(Long anneeScolaireId);
    FraisDivers save(FraisDivers frais);
    void deleteById(Long id);
}

