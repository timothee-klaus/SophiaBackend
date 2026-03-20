package com.sophia.backend.infrastructure.persistence.repository.adapter;

import com.sophia.backend.domain.model.Niveau;
import com.sophia.backend.domain.repository.NiveauRepository;
import com.sophia.backend.infrastructure.persistence.entity.NiveauEntity;
import com.sophia.backend.infrastructure.persistence.mapper.NiveauEntityMapper;
import com.sophia.backend.infrastructure.persistence.repository.NiveauJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class NiveauRepositoryAdapter implements NiveauRepository {
    private final NiveauJpaRepository jpaRepository;
    private final NiveauEntityMapper mapper;

    @Override
    public Optional<Niveau> findByUuid(UUID uuid) {
        return jpaRepository.findByUuid(uuid).map(mapper::toDomain);
    }

    @Override
    public List<Niveau> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Niveau> findByCycleUuid(UUID cycleUuid) {
        return jpaRepository.findByCycleUuid(cycleUuid).stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Niveau save(Niveau niveau) {
        NiveauEntity entity = mapper.toEntity(niveau);
        NiveauEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        jpaRepository.deleteByUuid(uuid);
    }
}
