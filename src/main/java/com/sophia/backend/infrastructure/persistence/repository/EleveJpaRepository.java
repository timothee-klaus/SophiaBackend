package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.EleveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EleveJpaRepository extends JpaRepository<EleveEntity, UUID> {
    Optional<EleveEntity> findByMatricule(String matricule);
}

