package com.sophia.backend.infrastructure.persistence.repository.adapter;
import com.sophia.backend.domain.model.Utilisateur;
import com.sophia.backend.domain.repository.UtilisateurRepository;
import com.sophia.backend.infrastructure.persistence.entity.UtilisateurEntity;
import com.sophia.backend.infrastructure.persistence.mapper.UtilisateurEntityMapper;
import com.sophia.backend.infrastructure.persistence.repository.UtilisateurJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@Repository
@RequiredArgsConstructor
public class UtilisateurRepositoryAdapter implements UtilisateurRepository {
    private final UtilisateurJpaRepository jpaRepository;
    private final UtilisateurEntityMapper mapper;
    @Override
    public Optional<Utilisateur> findById(UUID id) {
        return jpaRepository.findByUuid(id).map(mapper::toDomain);
    }
    @Override
    public Optional<Utilisateur> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(mapper::toDomain);
    }
    @Override
    public List<Utilisateur> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    @Override
    public Utilisateur save(Utilisateur utilisateur) {
        UtilisateurEntity entity = mapper.toEntity(utilisateur);
        UtilisateurEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }
    @Override
    public void deleteById(UUID id) {
        jpaRepository.findByUuid(id).ifPresent(u -> jpaRepository.deleteById(u.getId()));
    }
}
