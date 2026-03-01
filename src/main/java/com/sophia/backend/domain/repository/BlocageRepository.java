package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.Blocage;

import java.util.List;
import java.util.Optional;

public interface BlocageRepository {
    Optional<Blocage> findById(Long id);
    List<Blocage> findByInscriptionId(Long inscriptionId);
    Blocage save(Blocage blocage);
    void deleteById(Long id);
}

