package com.sophia.backend.infrastructure.persistence.repository.adapter;

import com.sophia.backend.domain.model.Cycle;
import com.sophia.backend.domain.repository.CycleRepository;
import com.sophia.backend.infrastructure.persistence.entity.CycleEntity;
import com.sophia.backend.infrastructure.persistence.mapper.CycleMapper;
import com.sophia.backend.infrastructure.persistence.repository.CycleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CycleRepositoryAdapter implements CycleRepository {
    private final CycleJpaRepository jpaRepository;
    private final CycleMapper mapper;

    @Override
    public Optional<Cycle> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Cycle> findByUuid(UUID uuid) {
        return jpaRepository.findByUuid(uuid).map(mapper::toDomain);
    }

    @Override
    public List<Cycle> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Cycle save(Cycle cycle) {
        CycleEntity entity = mapper.toEntity(cycle);
        CycleEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}

