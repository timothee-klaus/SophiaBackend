package com.sophia.backend.application.service;
import com.sophia.backend.domain.model.Utilisateur;
import com.sophia.backend.domain.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }
    public Optional<Utilisateur> findById(UUID id) {
        return utilisateurRepository.findById(id);
    }
    public Optional<Utilisateur> findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }
    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }
    public List<Utilisateur> findByRole(String role) {
        return utilisateurRepository.findByRole(role);
    }
    public Utilisateur create(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }
    public Utilisateur update(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }
    public void delete(UUID id) {
        utilisateurRepository.deleteById(id);
    }
    public Optional<Utilisateur> authentifier(String email, String motDePasse) {
        return utilisateurRepository.findByEmail(email);
    }
    public List<Utilisateur> obtenirSecretaires() {
        return this.findByRole("SECRETAIRE");
    }
    public List<Utilisateur> obtenirDirecteurs() {
        return this.findByRole("DIRECTEUR");
    }
}
