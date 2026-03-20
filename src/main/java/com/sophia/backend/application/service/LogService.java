package com.sophia.backend.application.service;
import com.sophia.backend.domain.enums.ActionLog;
import com.sophia.backend.domain.model.Log;
import com.sophia.backend.domain.repository.LogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@Transactional
public class LogService {
    private final LogRepository logRepository;
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }
    public Optional<Log> findById(Long id) {
        return logRepository.findById(id);
    }

    public Optional<Log> findByUuid(UUID uuid) {
        return logRepository.findByUuid(uuid);
    }

    public List<Log> findAll() {
        return logRepository.findAll();
    }

    public List<Log> findByUtilisateurId(UUID utilisateurUuid) {
        return logRepository.findByUtilisateurId(utilisateurUuid);
    }

    public List<Log> findByEntite(String entite) {
        return logRepository.findByEntite(entite);
    }

    public List<Log> findByEntiteAndEntiteId(String entite, String entiteId) {
        return logRepository.findByEntiteAndEntiteId(entite, entiteId);
    }

    public List<Log> findByAction(ActionLog action) {
        return logRepository.findByAction(action);
    }

    public Log create(Log log) {
        if (log.getUuid() == null) {
            log.setUuid(UUID.randomUUID());
        }
        return logRepository.save(log);
    }

    public void deleteByUuid(UUID uuid) {
        logRepository.deleteByUuid(uuid);
    }

    public void enregistrerCreation(String entite, String entiteId, String description) {
        Log log = new Log();
        log.setAction(ActionLog.CREATE);
        log.setEntite(entite);
        log.setEntiteId(entiteId);
        log.setDescription(description);
        this.create(log);
    }

    public void enregistrerModification(String entite, String entiteId,
                                       String anciennes_valeurs, String nouvelles_valeurs, String description) {
        Log log = new Log();
        log.setAction(ActionLog.UPDATE);
        log.setEntite(entite);
        log.setEntiteId(entiteId);
        log.setDescription(description);
        this.create(log);
    }

    public void enregistrerSuppression(String entite, String entiteId, String description) {
        Log log = new Log();
        log.setAction(ActionLog.DELETE);
        log.setEntite(entite);
        log.setEntiteId(entiteId);
        log.setDescription(description);
        this.create(log);
    }
    public void enregistrerConnexion(String description) {
        Log log = new Log();
        log.setAction(ActionLog.LOGIN);
        log.setDescription(description);
        this.create(log);
    }

    public void enregistrerDeconnexion(String description) {
        Log log = new Log();
        log.setAction(ActionLog.LOGOUT);
        log.setDescription(description);
        this.create(log);
    }
}
