package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.AnneeScolaire;

import java.util.List;
import java.util.Optional;

public interface AnneeScolaireRepository {
    Optional<AnneeScolaire> findById(Long id);
    List<AnneeScolaire> findAll();
    Optional<AnneeScolaire> findActive();
    AnneeScolaire save(AnneeScolaire annee);
    void deleteById(Long id);
}

