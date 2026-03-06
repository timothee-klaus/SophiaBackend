package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.InscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InscriptionJpaRepository extends JpaRepository<InscriptionEntity, Long> {
    List<InscriptionEntity> findByEleveId(UUID eleveId);
    List<InscriptionEntity> findByNiveauId(Long niveauId);
}

