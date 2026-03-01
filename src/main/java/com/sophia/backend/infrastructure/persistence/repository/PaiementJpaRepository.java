package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.PaiementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaiementJpaRepository extends JpaRepository<PaiementEntity, Long> {
    List<PaiementEntity> findByInscriptionId(Long inscriptionId);
}

