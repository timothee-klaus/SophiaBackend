package com.sophia.backend.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtService jwtService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = extractToken(request);

            if (token != null) {
                if (!jwtService.validateToken(token)) {
                    // Token invalide ou expiré
                    log.warn("Token JWT invalide ou expiré pour la requête: {}", request.getRequestURI());
                    sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED,
                        "Non authentifié",
                        "Le token JWT est invalide ou a expiré. Veuillez vous reconnecter.",
                        "Token invalide ou expiré"
                    );
                    return;
                }

                try {
                    String role = jwtService.extractRole(token);
                    String userId = jwtService.extractUserId(token).toString();
                    String email = jwtService.extractEmail(token);

                    log.debug("Utilisateur {} ({}) authentifié avec le rôle: {}", userId, email, role);

                    UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                            userId,
                            null,
                            Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role))
                        );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } catch (Exception e) {
                    log.error("Erreur lors de l'extraction des données du token: {}", e.getMessage());
                    sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED,
                        "Non authentifié",
                        "Impossible de traiter le token. Veuillez vous reconnecter.",
                        e.getMessage()
                    );
                    return;
                }
            }
            // Laisser passer les requêtes sans token (seront gérées par Spring Security)
        } catch (Exception e) {
            log.error("Erreur lors du filtrage JWT: {}", e.getMessage());
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "Erreur serveur",
                "Une erreur s'est produite lors de la vérification du token.",
                e.getMessage()
            );
            return;
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Extraire le token du header Authorization
     * Format: "Authorization: Bearer <token>"
     */
    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * Envoyer une réponse d'erreur JSON standardisée
     */
    private void sendErrorResponse(HttpServletResponse response, int status, String error, String message, String details)
            throws IOException {
        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");

        ErrorResponse errorResponse = new ErrorResponse(status, error, message, details);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse.toMap()));
    }
}

