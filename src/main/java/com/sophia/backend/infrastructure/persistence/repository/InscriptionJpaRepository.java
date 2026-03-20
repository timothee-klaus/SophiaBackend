package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.InscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InscriptionJpaRepository extends JpaRepository<InscriptionEntity, Long> {
    Optional<InscriptionEntity> findByUuid(UUID uuid);
    List<InscriptionEntity> findByEleveUuid(UUID eleveUuid);
    List<InscriptionEntity> findByNiveauUuid(UUID niveauUuid);
    void deleteByUuid(UUID uuid);
}

