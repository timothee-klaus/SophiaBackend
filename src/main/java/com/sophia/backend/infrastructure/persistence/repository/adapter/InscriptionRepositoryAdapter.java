package com.sophia.backend.infrastructure.persistence.repository.adapter;

import com.sophia.backend.domain.model.Inscription;
import com.sophia.backend.domain.repository.InscriptionRepository;
import com.sophia.backend.infrastructure.persistence.entity.InscriptionEntity;
import com.sophia.backend.infrastructure.persistence.mapper.InscriptionMapper;
import com.sophia.backend.infrastructure.persistence.repository.InscriptionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class InscriptionRepositoryAdapter implements InscriptionRepository {
    private final InscriptionJpaRepository jpaRepository;
    private final InscriptionMapper mapper;

    @Override
    public Optional<Inscription> findByUuid(UUID uuid) {
        return jpaRepository.findByUuid(uuid).map(mapper::toDomain);
    }

    @Override
    public List<Inscription> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Inscription> findByEleveUuid(UUID eleveUuid) {
        return jpaRepository.findByEleveUuid(eleveUuid).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Inscription> findByNiveauUuid(UUID niveauUuid) {
        return jpaRepository.findByNiveauUuid(niveauUuid).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Inscription save(Inscription inscription) {
        InscriptionEntity entity = mapper.toEntity(inscription);
        InscriptionEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        jpaRepository.deleteByUuid(uuid);
    }
}


