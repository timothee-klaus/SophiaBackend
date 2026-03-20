package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.PaiementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaiementJpaRepository extends JpaRepository<PaiementEntity, Long> {
    Optional<PaiementEntity> findByUuid(UUID uuid);
    List<PaiementEntity> findByInscriptionUuid(UUID inscriptionUuid);
    void deleteByUuid(UUID uuid);
}

