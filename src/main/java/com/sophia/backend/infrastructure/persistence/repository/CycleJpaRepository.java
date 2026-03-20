package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.CycleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CycleJpaRepository extends JpaRepository<CycleEntity, Long> {
    Optional<CycleEntity> findByUuid(UUID uuid);
}
