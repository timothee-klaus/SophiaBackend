package com.sophia.backend.infrastructure.persistence.repository.adapter;

import com.sophia.backend.domain.model.AnneeScolaire;
import com.sophia.backend.domain.repository.AnneeScolaireRepository;
import com.sophia.backend.infrastructure.persistence.entity.AnneeScolaireEntity;
import com.sophia.backend.infrastructure.persistence.mapper.AnneeScolaireEntityMapper;
import com.sophia.backend.infrastructure.persistence.repository.AnneeScolaireJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
public class AnneeScolaireRepositoryAdapter implements AnneeScolaireRepository {
    private final AnneeScolaireJpaRepository jpaRepository;
    private final AnneeScolaireEntityMapper mapper;

    @Override
    public Optional<AnneeScolaire> findByUuid(UUID uuid) {
        return jpaRepository.findByUuid(uuid).map(mapper::toDomain);
    }

    @Override
    public List<AnneeScolaire> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public AnneeScolaire save(AnneeScolaire anneeScolaire) {
        AnneeScolaireEntity entity = mapper.toEntity(anneeScolaire);
        AnneeScolaireEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        jpaRepository.deleteByUuid(uuid);
    }

    @Override
    public Optional<AnneeScolaire> findActive() {
        LocalDate today = LocalDate.now();
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .filter(a -> a.getDateDebut() != null && a.getDateFin() != null)
                .filter(a -> !today.isBefore(a.getDateDebut()) && !today.isAfter(a.getDateFin()))
                .findFirst();
    }
}
