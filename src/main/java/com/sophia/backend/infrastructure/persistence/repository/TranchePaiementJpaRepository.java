package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.TranchePaiementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranchePaiementJpaRepository extends JpaRepository<TranchePaiementEntity, Long> {
    List<TranchePaiementEntity> findByFraisScolaireId(Long fraisScolaireId);
}

