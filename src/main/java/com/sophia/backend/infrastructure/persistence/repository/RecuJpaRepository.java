package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.RecuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecuJpaRepository extends JpaRepository<RecuEntity, Long> {
    List<RecuEntity> findByPaiementId(Long paiementId);
}

