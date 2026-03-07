package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UtilisateurJpaRepository extends JpaRepository<UtilisateurEntity, Long> {
    Optional<UtilisateurEntity> findByUuid(UUID uuid);
    Optional<UtilisateurEntity> findByEmail(String email);
    List<UtilisateurEntity> findByRole(String role);
}

