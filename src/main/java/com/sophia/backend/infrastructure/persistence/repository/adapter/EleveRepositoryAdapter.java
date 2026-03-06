package com.sophia.backend.infrastructure.persistence.repository.adapter;

import com.sophia.backend.domain.model.Eleve;
import com.sophia.backend.domain.repository.EleveRepository;
import com.sophia.backend.infrastructure.persistence.entity.EleveEntity;
import com.sophia.backend.infrastructure.persistence.mapper.EleveEntityMapper;
import com.sophia.backend.infrastructure.persistence.repository.EleveJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class EleveRepositoryAdapter implements EleveRepository {
    private final EleveJpaRepository jpaRepository;
    private final EleveEntityMapper mapper;

    @Override
    public Optional<Eleve> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Eleve> findByMatricule(String matricule) {
        return jpaRepository.findByMatricule(matricule).map(mapper::toDomain);
    }

    @Override
    public List<Eleve> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Eleve> findByEtablissementId(Long etablissementId) {
        return jpaRepository.findByEtablissementId(etablissementId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Eleve save(Eleve eleve) {
        EleveEntity entity = mapper.toEntity(eleve);
        EleveEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}

