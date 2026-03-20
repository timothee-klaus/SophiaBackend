package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.FraisInscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FraisInscriptionJpaRepository extends JpaRepository<FraisInscriptionEntity, Long> {
    Optional<FraisInscriptionEntity> findByUuid(UUID uuid);
    List<FraisInscriptionEntity> findByCycleUuid(UUID cycleUuid);
    List<FraisInscriptionEntity> findByAnneeScolaireUuid(UUID anneeScolaireUuid);
    void deleteByUuid(UUID uuid);
}
