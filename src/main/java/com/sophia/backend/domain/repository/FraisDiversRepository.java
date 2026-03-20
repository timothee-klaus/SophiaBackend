package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.FraisDivers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FraisDiversRepository {
    Optional<FraisDivers> findByUuid(UUID uuid);
    Optional<FraisDivers> findById(Long id);
    List<FraisDivers> findAll();
    List<FraisDivers> findByNiveauUuid(UUID niveauUuid);
    List<FraisDivers> findByAnneeScolaireUuid(UUID anneeScolaireUuid);
    FraisDivers save(FraisDivers frais);
    void deleteByUuid(UUID uuid);
    void deleteById(Long id);
}
