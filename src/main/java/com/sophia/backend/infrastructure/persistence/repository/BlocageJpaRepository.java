package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.infrastructure.persistence.entity.BlocageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlocageJpaRepository extends JpaRepository<BlocageEntity, Long> {
    List<BlocageEntity> findByInscriptionId(Long inscriptionId);
}

