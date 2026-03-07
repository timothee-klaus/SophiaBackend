package com.sophia.backend.infrastructure.aspect;

import com.sophia.backend.application.service.LogService;
import com.sophia.backend.domain.enums.ActionLog;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @AfterReturning("@annotation(com.sophia.backend.infrastructure.aspect.LogCreate)")
    public void logCreate(JoinPoint joinPoint) {
        try {
            String userId = getCurrentUserId();
            String entity = getEntityName(joinPoint);

            logService.enregistrerCreation(
                userId != null ? UUID.fromString(userId) : UUID.randomUUID(),
                entity,
                "",
                "Creation de " + entity
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterReturning("@annotation(com.sophia.backend.infrastructure.aspect.LogUpdate)")
    public void logUpdate(JoinPoint joinPoint) {
        try {
            String userId = getCurrentUserId();
            String entity = getEntityName(joinPoint);

            logService.enregistrerModification(
                userId != null ? UUID.fromString(userId) : UUID.randomUUID(),
                entity,
                "",
                "",
                "",
                "Modification de " + entity
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterReturning("@annotation(com.sophia.backend.infrastructure.aspect.LogDelete)")
    public void logDelete(JoinPoint joinPoint) {
        try {
            String userId = getCurrentUserId();
            String entity = getEntityName(joinPoint);

            logService.enregistrerSuppression(
                userId != null ? UUID.fromString(userId) : UUID.randomUUID(),
                entity,
                "",
                "Suppression de " + entity
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getCurrentUserId() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return principal.toString();
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
            e.printStackTrace();
        }
        return "UNKNOWN";
    }

    private String getEntityName(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        return methodName.replace("create", "").replace("update", "").replace("delete", "");
    }
}




