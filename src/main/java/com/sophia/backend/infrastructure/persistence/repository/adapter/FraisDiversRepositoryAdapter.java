package com.sophia.backend.infrastructure.persistence.repository.adapter;

import com.sophia.backend.domain.model.FraisDivers;
import com.sophia.backend.domain.repository.FraisDiversRepository;
import com.sophia.backend.infrastructure.persistence.entity.FraisDiversEntity;
import com.sophia.backend.infrastructure.persistence.mapper.FraisDiversMapper;
import com.sophia.backend.infrastructure.persistence.repository.FraisDiversJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class FraisDiversRepositoryAdapter implements FraisDiversRepository {
    private final FraisDiversJpaRepository jpaRepository;
    private final FraisDiversMapper mapper;

    @Override
    public Optional<FraisDivers> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<FraisDivers> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<FraisDivers> findByNiveauId(Long niveauId) {
        return jpaRepository.findByNiveauId(niveauId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public FraisDivers save(FraisDivers frais) {
        FraisDiversEntity entity = mapper.toEntity(frais);
        FraisDiversEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<FraisDivers> findByAnneeScolaireId(Long anneeScolaireId) {
        return jpaRepository.findByAnneeScolaireId(anneeScolaireId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
