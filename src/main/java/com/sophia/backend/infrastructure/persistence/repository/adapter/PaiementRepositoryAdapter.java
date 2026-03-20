package com.sophia.backend.infrastructure.persistence.repository.adapter;

import com.sophia.backend.domain.model.Paiement;
import com.sophia.backend.domain.repository.PaiementRepository;
import com.sophia.backend.infrastructure.persistence.entity.PaiementEntity;
import com.sophia.backend.infrastructure.persistence.mapper.PaiementEntityMapper;
import com.sophia.backend.infrastructure.persistence.repository.PaiementJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PaiementRepositoryAdapter implements PaiementRepository {
    private final PaiementJpaRepository jpaRepository;
    private final PaiementEntityMapper mapper;

    @Override
    public Optional<Paiement> findByUuid(UUID uuid) {
        return jpaRepository.findByUuid(uuid).map(mapper::toDomain);
    }

    @Override
    public List<Paiement> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Paiement> findByInscriptionUuid(UUID inscriptionUuid) {
        return jpaRepository.findByInscriptionUuid(inscriptionUuid).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Paiement save(Paiement paiement) {
        PaiementEntity entity = mapper.toEntity(paiement);
        PaiementEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        jpaRepository.deleteByUuid(uuid);
    }
}


