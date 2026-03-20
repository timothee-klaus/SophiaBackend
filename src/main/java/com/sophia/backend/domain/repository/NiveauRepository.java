package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.Niveau;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NiveauRepository {
    Optional<Niveau> findByUuid(UUID uuid);
    List<Niveau> findAll();
    List<Niveau> findByCycleUuid(UUID cycleUuid);
    Niveau save(Niveau niveau);
    void deleteByUuid(UUID uuid);
}

