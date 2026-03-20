package com.sophia.backend.infrastructure.persistence.repository.adapter;

import com.sophia.backend.domain.model.Notification;
import com.sophia.backend.domain.repository.NotificationRepository;
import com.sophia.backend.infrastructure.persistence.entity.NotificationEntity;
import com.sophia.backend.infrastructure.persistence.mapper.NotificationEntityMapper;
import com.sophia.backend.infrastructure.persistence.repository.NotificationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class NotificationRepositoryAdapter implements NotificationRepository {
    private final NotificationJpaRepository jpaRepository;
    private final NotificationEntityMapper mapper;

    @Override
    public Optional<Notification> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Notification> findByUuid(java.util.UUID uuid) {
        return jpaRepository.findByUuid(uuid).map(mapper::toDomain);
    }


    @Override
    public Notification save(Notification notification) {
        NotificationEntity entity = mapper.toEntity(notification);
        NotificationEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public void deleteByUuid(java.util.UUID uuid) {
        jpaRepository.findByUuid(uuid).ifPresent(e -> jpaRepository.deleteById(e.getId()));
    }

    @Override
    public List<Notification> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Notification> findByDestinataireUuid(java.util.UUID destinataireUuid) {
        return jpaRepository.findByDestinataireUuid(destinataireUuid).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Notification> findByDestinataireUuidAndLuFalse(java.util.UUID destinataireUuid) {
        return jpaRepository.findByDestinataireUuidAndLuFalse(destinataireUuid).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Notification> findByExpediteurUuid(java.util.UUID expeditriceUuid) {
        return jpaRepository.findByExpediteurUuid(expeditriceUuid).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}

