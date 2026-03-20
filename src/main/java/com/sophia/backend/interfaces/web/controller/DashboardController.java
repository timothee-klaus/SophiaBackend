package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.DashboardSummaryDTO;
import com.sophia.backend.application.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Dashboard", description = "Résumé KPI et données récentes pour le tableau de bord")
@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @Operation(summary = "Résumé du dashboard", description = "Retourne les KPIs, données récentes, paiements par jour, répartition par cycle et journal d'audit.")
    @GetMapping("/summary")
    public ResponseEntity<DashboardSummaryDTO> summary() {
        return ResponseEntity.ok(dashboardService.getDashboardSummary());
    }
}

