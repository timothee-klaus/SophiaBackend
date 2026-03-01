package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.Utilisateur;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UtilisateurRepository {
    Optional<Utilisateur> findById(UUID id);
    Optional<Utilisateur> findByEmail(String email);
    List<Utilisateur> findAll();
    Utilisateur save(Utilisateur utilisateur);
    void deleteById(UUID id);
}

