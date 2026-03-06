package com.sophia.backend.application.service;
import com.sophia.backend.domain.model.Log;
import com.sophia.backend.domain.repository.LogRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class LogService {
    private final LogRepository logRepository;
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }
    public Optional<Log> findById(Long id) {
        return logRepository.findById(id);
    }
    public List<Log> findAll() {
        return logRepository.findAll();
    }
    public List<Log> findByUtilisateurId(UUID utilisateurId) {
        return logRepository.findByUtilisateurId(utilisateurId);
    }
    public Log create(Log log) {
        return logRepository.save(log);
    }
    public void delete(Long id) {
        logRepository.deleteById(id);
    }
    public void enregistrerCreation(UUID utilisateurId, String entite, String entiteId, String description) {
        Log log = new Log();
        log.setUtilisateurId(utilisateurId);
        log.setAction("CREATE");
        log.setEntite(entite);
        log.setEntiteId(entiteId);
        log.setDescription(description);
        this.create(log);
    }
    public void enregistrerModification(UUID utilisateurId, String entite, String entiteId, 
                                       String anciennes_valeurs, String nouvelles_valeurs, String description) {
        Log log = new Log();
        log.setUtilisateurId(utilisateurId);
        log.setAction("UPDATE");
        log.setEntite(entite);
        log.setEntiteId(entiteId);
        log.setAnciennes_valeurs(anciennes_valeurs);
        log.setNouvelles_valeurs(nouvelles_valeurs);
        log.setDescription(description);
        this.create(log);
    }
    public void enregistrerSuppression(UUID utilisateurId, String entite, String entiteId, String description) {
        Log log = new Log();
        log.setUtilisateurId(utilisateurId);
        log.setAction("DELETE");
        log.setEntite(entite);
        log.setEntiteId(entiteId);
        log.setDescription(description);
        this.create(log);
    }
    public void enregistrerConnexion(UUID utilisateurId, String adresseIp, String userAgent) {
        Log log = new Log();
        log.setUtilisateurId(utilisateurId);
        log.setAction("LOGIN");
        log.setDescription("Connexion utilisateur");
        log.setIpAdresse(adresseIp);
        log.setUserAgent(userAgent);
        this.create(log);
    }
    public void enregistrerDeconnexion(UUID utilisateurId) {
        Log log = new Log();
        log.setUtilisateurId(utilisateurId);
        log.setAction("LOGOUT");
        log.setDescription("Déconnexion utilisateur");
        this.create(log);
    }
    public List<Log> obtenirHistoriquUtilisateur(UUID utilisateurId) {
        return this.findByUtilisateurId(utilisateurId);
    }
}
