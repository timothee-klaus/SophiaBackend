package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.TranchePaiementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TranchePaiementJpaRepository extends JpaRepository<TranchePaiementEntity, Long> {
    Optional<TranchePaiementEntity> findByUuid(UUID uuid);
    List<TranchePaiementEntity> findByFraisScolaireUuid(UUID fraisScolaireUuid);
    void deleteByUuid(UUID uuid);
}
