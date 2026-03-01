package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.domain.model.Paiement;
import com.sophia.backend.domain.repository.PaiementRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class InMemoryPaiementRepository implements PaiementRepository {
    private final Map<Long, Paiement> store = new ConcurrentHashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    @Override
    public Optional<Paiement> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Paiement> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Paiement> findByInscriptionId(Long inscriptionId) {
        return store.values().stream()
                .filter(p -> Objects.equals(p.getInscriptionId(), inscriptionId))
                .collect(Collectors.toList());
    }

    @Override
    public Paiement save(Paiement paiement) {
        if (paiement.getId() == null) {
            paiement.setId(idGen.getAndIncrement());
        }
        store.put(paiement.getId(), paiement);
        return paiement;
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}

