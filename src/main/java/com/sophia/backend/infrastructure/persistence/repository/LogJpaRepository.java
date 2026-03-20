package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.domain.enums.ActionLog;
import com.sophia.backend.infrastructure.persistence.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LogJpaRepository extends JpaRepository<LogEntity, Long> {
    Optional<LogEntity> findByUuid(UUID uuid);
    List<LogEntity> findByUtilisateurId(UUID utilisateurId);
    List<LogEntity> findByEntite(String entite);
    List<LogEntity> findByEntiteAndEntiteId(String entite, String entiteId);
    List<LogEntity> findByAction(ActionLog action);
    void deleteByUuid(UUID uuid);
}

