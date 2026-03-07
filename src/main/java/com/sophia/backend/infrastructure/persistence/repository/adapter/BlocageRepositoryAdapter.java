package com.sophia.backend.infrastructure.persistence.repository.adapter;

import com.sophia.backend.domain.model.Blocage;
import com.sophia.backend.domain.repository.BlocageRepository;
import com.sophia.backend.infrastructure.persistence.entity.BlocageEntity;
import com.sophia.backend.infrastructure.persistence.mapper.BlocageMapper;
import com.sophia.backend.infrastructure.persistence.repository.BlocageJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BlocageRepositoryAdapter implements BlocageRepository {
    private final BlocageJpaRepository jpaRepository;
    private final BlocageMapper mapper;

    @Override
    public Optional<Blocage> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Blocage> findByInscriptionId(Long inscriptionId) {
        return jpaRepository.findByInscriptionId(inscriptionId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Blocage save(Blocage blocage) {
        BlocageEntity entity = mapper.toEntity(blocage);
        BlocageEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
    @Override
    public List<Blocage> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Blocage> findActiveBlocks() {
        return jpaRepository.findByEstActifTrue().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
