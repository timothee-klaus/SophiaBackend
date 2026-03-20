package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.enums.ActionLog;
import com.sophia.backend.domain.model.Log;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LogRepository {
    Optional<Log> findById(Long id);
    Optional<Log> findByUuid(UUID uuid);
    List<Log> findAll();
    List<Log> findByUtilisateurId(UUID utilisateurUuid);
    List<Log> findByEntite(String entite);
    List<Log> findByEntiteAndEntiteId(String entite, String entiteId);
    List<Log> findByAction(ActionLog action);
    Log save(Log log);
    void deleteById(Long id);
    void deleteByUuid(UUID uuid);
}

