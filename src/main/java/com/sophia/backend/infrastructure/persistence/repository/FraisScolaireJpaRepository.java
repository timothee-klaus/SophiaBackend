package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.FraisScolaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FraisScolaireJpaRepository extends JpaRepository<FraisScolaireEntity, Long> {
    Optional<FraisScolaireEntity> findByUuid(UUID uuid);
    List<FraisScolaireEntity> findByNiveauUuid(UUID niveauUuid);
    List<FraisScolaireEntity> findByAnneeScolaireUuid(UUID anneeScolaireUuid);
    void deleteByUuid(UUID uuid);
}

