package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.FraisScolaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FraisScolaireJpaRepository extends JpaRepository<FraisScolaireEntity, Long> {
    List<FraisScolaireEntity> findByNiveauId(Long niveauId);
}

