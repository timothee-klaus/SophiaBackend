package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.NiveauEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NiveauJpaRepository extends JpaRepository<NiveauEntity, Long> {
    Optional<NiveauEntity> findByUuid(UUID uuid);
    List<NiveauEntity> findByCycleId(Long cycleId);
    List<NiveauEntity> findByCycleUuid(UUID cycleUuid);
    List<NiveauEntity> findByEtablissementId(Long etablissementId);
    void deleteByUuid(UUID uuid);
}
