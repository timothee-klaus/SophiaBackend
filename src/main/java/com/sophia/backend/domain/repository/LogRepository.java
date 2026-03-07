package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.Log;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LogRepository {
    Optional<Log> findById(Long id);
    List<Log> findAll();
    List<Log> findByUtilisateurId(UUID utilisateurId);
    Log save(Log log);
    void deleteById(Long id);
}

