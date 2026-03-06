package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LogJpaRepository extends JpaRepository<LogEntity, Long> {
    List<LogEntity> findByUtilisateurId(UUID utilisateurId);
}

