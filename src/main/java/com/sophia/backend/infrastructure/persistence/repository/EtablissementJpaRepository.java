package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.EtablissementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtablissementJpaRepository extends JpaRepository<EtablissementEntity, Long> {
    List<EtablissementEntity> findByStatut(String statut);
}

