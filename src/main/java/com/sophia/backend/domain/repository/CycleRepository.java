package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.Cycle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CycleRepository {
    Optional<Cycle> findById(Long id);
    Optional<Cycle> findByUuid(UUID uuid);
    List<Cycle> findAll();
    Cycle save(Cycle cycle);
    void deleteById(Long id);
}

