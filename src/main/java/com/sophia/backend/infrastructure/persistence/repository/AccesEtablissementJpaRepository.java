package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.AccesEtablissementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccesEtablissementJpaRepository extends JpaRepository<AccesEtablissementEntity, Long> {
    List<AccesEtablissementEntity> findByUtilisateurId(UUID utilisateurId);
    List<AccesEtablissementEntity> findByEtablissementId(Long etablissementId);
}

