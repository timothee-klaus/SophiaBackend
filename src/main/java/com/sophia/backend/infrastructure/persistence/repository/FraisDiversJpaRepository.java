package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.FraisDiversEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FraisDiversJpaRepository extends JpaRepository<FraisDiversEntity, Long> {
    Optional<FraisDiversEntity> findByUuid(UUID uuid);
    List<FraisDiversEntity> findByNiveauUuid(UUID niveauUuid);
    List<FraisDiversEntity> findByAnneeScolaireUuid(UUID anneeScolaireUuid);
    void deleteByUuid(UUID uuid);
}

