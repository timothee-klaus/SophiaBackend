package com.sophia.backend.infrastructure.persistence.repository.adapter;

import com.sophia.backend.domain.model.Document;
import com.sophia.backend.domain.repository.DocumentRepository;
import com.sophia.backend.infrastructure.persistence.entity.DocumentEntity;
import com.sophia.backend.infrastructure.persistence.mapper.DocumentMapper;
import com.sophia.backend.infrastructure.persistence.repository.DocumentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class DocumentRepositoryAdapter implements DocumentRepository {
    private final DocumentJpaRepository jpaRepository;
    private final DocumentMapper mapper;

    @Override
    public Optional<Document> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Document> findByEleveId(UUID eleveId) {
        return jpaRepository.findByEleveId(eleveId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Document save(Document document) {
        DocumentEntity entity = mapper.toEntity(document);
        DocumentEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}

