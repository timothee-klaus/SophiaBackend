package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.FraisInscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FraisInscriptionJpaRepository extends JpaRepository<FraisInscriptionEntity, Long> {
    List<FraisInscriptionEntity> findByCycleId(Long cycleId);
    List<FraisInscriptionEntity> findByAnneeScolaireId(Long anneeScolaireId);
}

