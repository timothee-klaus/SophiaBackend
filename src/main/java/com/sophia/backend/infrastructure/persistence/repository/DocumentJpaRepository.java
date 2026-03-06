package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentJpaRepository extends JpaRepository<DocumentEntity, Long> {
    List<DocumentEntity> findByEleveId(UUID eleveId);
}

