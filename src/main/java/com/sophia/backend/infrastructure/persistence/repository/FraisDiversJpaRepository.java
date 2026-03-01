package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.FraisDiversEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FraisDiversJpaRepository extends JpaRepository<FraisDiversEntity, Long> {
    List<FraisDiversEntity> findByNiveauId(Long niveauId);
}

