package com.sophia.backend.infrastructure.security;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Réponse d'erreur standardisée pour les requêtes
 */
public class ErrorResponse {
    private int status;
    private String error;
    private String message;
    private String details;
    private LocalDateTime timestamp;

    public ErrorResponse(int status, String error, String message, String details) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Erreur 400 - Requête invalide
     */
    public static ErrorResponse badRequest(String message, String details) {
        return new ErrorResponse(400, "Bad Request", message, details);
    }

    /**
     * Erreur 401 - Non authentifié
     */
    public static ErrorResponse unauthorized(String message, String details) {
        return new ErrorResponse(401, "Unauthorized", message, details);
    }

    /**
     * Erreur 403 - Accès refusé
     */
    public static ErrorResponse forbidden(String message, String details) {
        return new ErrorResponse(403, "Forbidden", message, details);
    }

    /**
     * Erreur 404 - Non trouvé
     */
    public static ErrorResponse notFound(String message, String details) {
        return new ErrorResponse(404, "Not Found", message, details);
    }

    /**
     * Erreur 500 - Erreur serveur
     */
    public static ErrorResponse internalServerError(String message, String details) {
        return new ErrorResponse(500, "Internal Server Error", message, details);
    }

    /**
     * Convertir en Map pour la sérialisation JSON
     */
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", this.status);
        map.put("error", this.error);
        map.put("message", this.message);
        map.put("details", this.details);
        map.put("timestamp", this.timestamp);
        return map;
    }

    // Getters et Setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

