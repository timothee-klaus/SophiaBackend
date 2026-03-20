package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.AnneeScolaire;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnneeScolaireRepository {
    Optional<AnneeScolaire> findByUuid(UUID uuid);
    List<AnneeScolaire> findAll();
    Optional<AnneeScolaire> findActive();
    AnneeScolaire save(AnneeScolaire annee);
    void deleteByUuid(UUID uuid);
}
