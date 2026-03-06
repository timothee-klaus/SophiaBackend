package com.sophia.backend.infrastructure.persistence.repository.adapter;

import com.sophia.backend.domain.model.TranchePaiement;
import com.sophia.backend.domain.repository.TranchePaiementRepository;
import com.sophia.backend.infrastructure.persistence.entity.TranchePaiementEntity;
import com.sophia.backend.infrastructure.persistence.mapper.TranchePaiementMapper;
import com.sophia.backend.infrastructure.persistence.repository.TranchePaiementJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TranchePaiementRepositoryAdapter implements TranchePaiementRepository {
    private final TranchePaiementJpaRepository jpaRepository;
    private final TranchePaiementMapper mapper;

    @Override
    public Optional<TranchePaiement> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<TranchePaiement> findByFraisScolaireId(Long fraisScolaireId) {
        return jpaRepository.findByFraisScolaireId(fraisScolaireId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public TranchePaiement save(TranchePaiement tranche) {
        TranchePaiementEntity entity = mapper.toEntity(tranche);
        TranchePaiementEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}

