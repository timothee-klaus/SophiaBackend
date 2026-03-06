package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.AnneeScolaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnneeScolaireJpaRepository extends JpaRepository<AnneeScolaireEntity, Long> {
    Optional<AnneeScolaireEntity> findByEstActive(Boolean estActive);
}

