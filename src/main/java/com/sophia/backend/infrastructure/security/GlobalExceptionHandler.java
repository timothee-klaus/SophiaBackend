package com.sophia.backend.infrastructure.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gérer les erreurs 400 - Requête malformée
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(IllegalArgumentException ex) {
        Map<String, Object> errorResponse = buildErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Requête invalide",
            "La requête contient des données malformées ou invalides. Veuillez vérifier vos paramètres.",
            ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Gérer les erreurs 401 - Non authentifié
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, Object>> handleUnauthorized(AuthenticationException ex) {
        Map<String, Object> errorResponse = buildErrorResponse(
            HttpStatus.UNAUTHORIZED.value(),
            "Non authentifié",
            "Vous devez fournir un token JWT valide dans le header 'Authorization: Bearer <token>'.",
            "Token manquant, invalide ou expiré"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Gérer les erreurs 403 - Accès refusé
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> handleForbidden(AccessDeniedException ex) {
        Map<String, Object> errorResponse = buildErrorResponse(
            HttpStatus.FORBIDDEN.value(),
            "Accès refusé",
            "Vous n'avez pas les permissions nécessaires pour accéder à cette ressource. Votre rôle ne permet pas cette action.",
            "Permissions insuffisantes pour cette opération"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    /**
     * Gérer les erreurs 404 - Ressource non trouvée
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NoHandlerFoundException ex) {
        Map<String, Object> errorResponse = buildErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            "Ressource non trouvée",
            "L'endpoint demandé n'existe pas. Veuillez vérifier l'URL.",
            "Endpoint: " + ex.getRequestURL()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Gérer les erreurs 500 - Erreur serveur
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleInternalServerError(Exception ex) {
        Map<String, Object> errorResponse = buildErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Erreur serveur",
            "Une erreur inattendue s'est produite. Veuillez contacter l'administrateur.",
            ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Construire la réponse d'erreur standardisée
     */
    private Map<String, Object> buildErrorResponse(int status, String error, String message, String details) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("error", error);
        response.put("message", message);
        response.put("details", details);
        response.put("timestamp", LocalDateTime.now());
        return response;
    }
}

