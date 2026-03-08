package com.sophia.backend.infrastructure.aspect;

import com.sophia.backend.application.service.LogService;
import com.sophia.backend.domain.enums.ActionLog;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final LogService logService;

    // Capture tous les POST (CREATE)
    @AfterReturning("execution(* com.sophia.backend.interfaces.web.controller.*Controller.create(*))")
    public void logCreate(JoinPoint joinPoint) {
        try {
            String entity = extractEntity(joinPoint.getSignature().getDeclaringTypeName());
            String userId = getCurrentUserId();
            String ipAddress = getClientIp();

            logService.enregistrerCreation(
                userId != null ? UUID.fromString(userId) : UUID.randomUUID(),
                entity,
                "",
                "Creation de " + entity
            );
        } catch (Exception e) {
            // Ne pas bloquer
        }
    }

    // Capture tous les PUT (UPDATE)
    @AfterReturning("execution(* com.sophia.backend.interfaces.web.controller.*Controller.update(*))")
    public void logUpdate(JoinPoint joinPoint) {
        try {
            String entity = extractEntity(joinPoint.getSignature().getDeclaringTypeName());
            String userId = getCurrentUserId();

            logService.enregistrerModification(
                userId != null ? UUID.fromString(userId) : UUID.randomUUID(),
                entity,
                "",
                "",
                "",
                "Modification de " + entity
            );
        } catch (Exception e) {
            // Ne pas bloquer
        }
    }

    // Capture tous les DELETE
    @AfterReturning("execution(* com.sophia.backend.interfaces.web.controller.*Controller.delete(*))")
    public void logDelete(JoinPoint joinPoint) {
        try {
            String entity = extractEntity(joinPoint.getSignature().getDeclaringTypeName());
            String userId = getCurrentUserId();

            logService.enregistrerSuppression(
                userId != null ? UUID.fromString(userId) : UUID.randomUUID(),
                entity,
                "",
                "Suppression de " + entity
            );
        } catch (Exception e) {
            // Ne pas bloquer
        }
    }

    private String extractEntity(String className) {
        String[] parts = className.split("\\.");
        String controllerName = parts[parts.length - 1];
        return controllerName.replace("Controller", "").toUpperCase();
    }

    private String getCurrentUserId() {
        try {
            // À implémenter avec Spring Security
            return "system-user";
        } catch (Exception e) {
            return null;
        }
    }

    private String getClientIp() {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                String xForwardedFor = request.getHeader("X-Forwarded-For");
                if (xForwardedFor == null || xForwardedFor.isEmpty()) {
                    return request.getRemoteAddr();
                }
                return xForwardedFor.split(",")[0];
            }
        } catch (Exception e) {
            // Ne pas bloquer
        }
        return "UNKNOWN";
    }
}




