package com.sophia.backend.application.service;

import com.sophia.backend.application.dto.*;
import com.sophia.backend.domain.enums.StatutDossier;
import com.sophia.backend.domain.enums.StatutInscription;
import com.sophia.backend.domain.model.AnneeScolaire;
import com.sophia.backend.domain.model.Cycle;
import com.sophia.backend.domain.model.Inscription;
import com.sophia.backend.domain.model.Niveau;
import com.sophia.backend.domain.model.Paiement;
import com.sophia.backend.domain.model.Log;
import com.sophia.backend.domain.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final EleveRepository eleveRepository;
    private final InscriptionRepository inscriptionRepository;
    private final PaiementRepository paiementRepository;
    private final CycleRepository cycleRepository;
    private final NiveauRepository niveauRepository;
    private final AnneeScolaireService anneeScolaireService;
    private final LogRepository logRepository;

    public DashboardService(EleveRepository eleveRepository,
                            InscriptionRepository inscriptionRepository,
                            PaiementRepository paiementRepository,
                            CycleRepository cycleRepository,
                            NiveauRepository niveauRepository,
                            AnneeScolaireService anneeScolaireService,
                            LogRepository logRepository) {
        this.eleveRepository = eleveRepository;
        this.inscriptionRepository = inscriptionRepository;
        this.paiementRepository = paiementRepository;
        this.cycleRepository = cycleRepository;
        this.niveauRepository = niveauRepository;
        this.anneeScolaireService = anneeScolaireService;
        this.logRepository = logRepository;
    }

    /**
     * Fournit un résumé complet pour le dashboard. Données statiques pour rétablir l'endpoint
     * en attendant un branchement sur les agrégations réelles.
     */
    public DashboardSummaryDTO getDashboardSummary() {
        LocalDateTime now = LocalDateTime.now();
        LocalDate today = now.toLocalDate();
        YearMonth currentMonth = YearMonth.from(today);
        int currentYear = today.getYear();

        List<Inscription> inscriptions = inscriptionRepository.findAll();
        List<Paiement> paiements = paiementRepository.findAll();
        List<Niveau> niveaux = niveauRepository.findAll();
        Map<UUID, Niveau> niveauByUuid = niveaux.stream()
                .filter(n -> n.getUuid() != null)
                .collect(Collectors.toMap(Niveau::getUuid, n -> n, (a, b) -> a));

        List<Cycle> cycles = cycleRepository.findAll();
        Map<UUID, Cycle> cycleByUuid = cycles.stream()
                .filter(c -> c.getUuid() != null)
                .collect(Collectors.toMap(Cycle::getUuid, c -> c, (a, b) -> a));

        Optional<AnneeScolaire> activeYearOpt = anneeScolaireService.obtenirAnneeScolaireActive();
        UUID activeYearUuid = activeYearOpt.map(AnneeScolaire::getUuid).orElse(null);

        // KPIs
        long totalStudentsRegistered = eleveRepository.findAll().size();

        Set<UUID> activeStudents = inscriptions.stream()
                .filter(insc -> insc.getStatut() == StatutInscription.ACTIVE)
                .map(Inscription::getEleveUuid)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        long totalStudentsActive = activeStudents.size();

        long enrollmentsCurrentYear = inscriptions.stream()
                .filter(insc -> activeYearUuid != null && activeYearUuid.equals(insc.getAnneeScolaireUuid()))
                .count();

        long totalEnrollmentsAllTime = inscriptions.size();

        // Paiements
        BigDecimal paymentsThisMonth = sumPaiements(paiements, p -> YearMonth.from(getPaymentDate(p)).equals(currentMonth));
        BigDecimal paymentsThisYear = sumPaiements(paiements, p -> getPaymentDate(p).getYear() == currentYear);
        long paymentCountThisMonth = paiements.stream().filter(p -> YearMonth.from(getPaymentDate(p)).equals(currentMonth)).count();
        long paymentCountThisYear = paiements.stream().filter(p -> getPaymentDate(p).getYear() == currentYear).count();

        // Montant total dû : pas de calcul de frais dans le modèle actuel, on retourne null si inconnu
        BigDecimal totalAmountDue = null;
        Double recoveryRate = (totalAmountDue == null || BigDecimal.ZERO.compareTo(totalAmountDue) == 0)
                ? null
                : paymentsThisYear.divide(totalAmountDue, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).doubleValue();

        long elevesBloques = inscriptions.stream()
                .filter(insc -> insc.isBloqueExamen() || insc.isBloqueEvaluation())
                .map(Inscription::getEleveUuid)
                .filter(Objects::nonNull)
                .distinct()
                .count();

        long inscriptionsEnAttente = inscriptions.stream()
                .filter(insc -> insc.getStatut() == null)
                .count();

        long incompleteStudentFiles = eleveRepository.findAll().stream()
                .filter(e -> e.getStatutDossier() == StatutDossier.INCOMPLET)
                .count();

        DashboardKPIsDTO kpis = DashboardKPIsDTO.builder()
                .totalStudentsActive(totalStudentsActive)
                .totalStudentsRegistered(totalStudentsRegistered)
                .enrollmentsCurrentYear(enrollmentsCurrentYear)
                .totalEnrollmentsAllTime(totalEnrollmentsAllTime)
                .paymentsThisMonth(paymentsThisMonth)
                .paymentsThisYear(paymentsThisYear)
                .paymentCountThisMonth(paymentCountThisMonth)
                .paymentCountThisYear(paymentCountThisYear)
                .totalAmountDue(totalAmountDue)
                .recoveryRate(recoveryRate)
                .elevesBloques(elevesBloques)
                .inscriptionsEnAttente(inscriptionsEnAttente)
                .incompleteStudentFiles(incompleteStudentFiles)
                .build();

        DashboardRecentDataDTO recentData = DashboardRecentDataDTO.builder()
                .recentInscriptions(buildRecentInscriptions(inscriptions, niveauByUuid, activeYearOpt))
                .recentPayments(buildRecentPayments(paiements))
                .build();

        List<DashboardPaiementJourDTO> paiementsParJour = buildPaiementsParJour(paiements, today.minusDays(6), today);
        BigDecimal montantPaiementsJour = paiementsParJour.stream()
                .map(DashboardPaiementJourDTO::getMontant)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<DashboardRepartitionCycleDTO> repartitionParCycle = buildRepartitionParCycle(inscriptions, niveauByUuid, cycleByUuid);
        List<DashboardJournalAuditDTO> journalAudit = buildJournalAudit();

        return DashboardSummaryDTO.builder()
                .kpis(kpis)
                .recentData(recentData)
                .paiementsParJour(paiementsParJour)
                .montantPaiementsJour(montantPaiementsJour)
                .repartitionParCycle(repartitionParCycle)
                .journalAudit(journalAudit)
                .generatedAt(now)
                .apiVersion("1.0")
                .build();
    }

    private LocalDateTime getPaymentDate(Paiement p) {
        return Optional.ofNullable(p.getDatePaiement()).orElseGet(() ->
                Optional.ofNullable(p.getCreatedAt()).orElse(LocalDateTime.MIN));
    }

    private BigDecimal sumPaiements(List<Paiement> paiements, java.util.function.Predicate<Paiement> predicate) {
        return paiements.stream()
                .filter(predicate)
                .map(Paiement::getMontant)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<DashboardRecentInscriptionDTO> buildRecentInscriptions(List<Inscription> inscriptions,
                                                                        Map<UUID, Niveau> niveauByUuid,
                                                                        Optional<AnneeScolaire> activeYearOpt) {
        return inscriptions.stream()
                .sorted(Comparator.comparing(Inscription::getCreatedAt, Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .limit(5)
                .map(insc -> {
                    Niveau niveau = niveauByUuid.get(insc.getNiveauUuid());
                    String niveauLabel = niveau != null ? niveau.getNom() : null;
                    String anneeLabel = activeYearOpt.map(AnneeScolaire::getLibelle).orElse(null);
                    UUID niveauUuid = niveau != null ? niveau.getUuid() : null;
                    UUID cycleUuid = niveau != null ? niveau.getCycleUuid() : null;

                    return DashboardRecentInscriptionDTO.builder()
                            .uuid(insc.getUuid())
                            .eleveUuid(insc.getEleveUuid())
                            .studentFullName(null)
                            .niveauUuid(niveauUuid)
                            .niveauLabel(niveauLabel)
                            .anneeScolaireUuid(insc.getAnneeScolaireUuid())
                            .anneeScolaireLabel(anneeLabel)
                            .dateInscription(insc.getDateInscription())
                            .statut(insc.getStatut())
                            .bloqueExamen(insc.isBloqueExamen())
                            .bloqueEvaluation(insc.isBloqueEvaluation())
                            .createdAt(insc.getCreatedAt())
                            .build();
                })
                .collect(Collectors.toList());
    }

    private List<DashboardRecentPaiementDTO> buildRecentPayments(List<Paiement> paiements) {
        return paiements.stream()
                .sorted(Comparator.comparing(this::getPaymentDate).reversed())
                .limit(5)
                .map(p -> DashboardRecentPaiementDTO.builder()
                        .uuid(p.getUuid())
                        .inscriptionUuid(p.getInscriptionUuid())
                        .eleveUuid(null)
                        .studentFullName(null)
                        .typePaiement(p.getTypePaiement())
                        .montant(p.getMontant())
                        .modePaiement(p.getModePaiement())
                        .datePaiement(p.getDatePaiement())
                        .commentaire(p.getCommentaire())
                        .createdAt(p.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    private List<DashboardPaiementJourDTO> buildPaiementsParJour(List<Paiement> paiements, LocalDate start, LocalDate end) {
        Map<LocalDate, BigDecimal> summed = paiements.stream()
                .filter(p -> {
                    LocalDate d = getPaymentDate(p).toLocalDate();
                    return (!d.isBefore(start) && !d.isAfter(end));
                })
                .collect(Collectors.groupingBy(p -> getPaymentDate(p).toLocalDate(), Collectors.mapping(Paiement::getMontant,
                        Collectors.reducing(BigDecimal.ZERO, (a, b) -> a.add(b != null ? b : BigDecimal.ZERO)))));

        DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;
        List<DashboardPaiementJourDTO> results = new ArrayList<>();
        LocalDate cursor = start;
        while (!cursor.isAfter(end)) {
            BigDecimal montant = summed.getOrDefault(cursor, BigDecimal.ZERO);
            results.add(DashboardPaiementJourDTO.builder().date(fmt.format(cursor)).montant(montant).build());
            cursor = cursor.plusDays(1);
        }
        return results;
    }

    private List<DashboardRepartitionCycleDTO> buildRepartitionParCycle(List<Inscription> inscriptions,
                                                                        Map<UUID, Niveau> niveauByUuid,
                                                                        Map<UUID, Cycle> cycleByUuid) {
        Map<String, Long> counts = new HashMap<>();
        inscriptions.forEach(insc -> {
            Niveau niveau = niveauByUuid.get(insc.getNiveauUuid());
            Cycle cycle = niveau != null ? cycleByUuid.get(niveau.getCycleUuid()) : null;
            String cycleName = cycle != null ? cycle.getNom() : "Inconnu";
            counts.merge(cycleName, 1L, Long::sum);
        });
        long total = counts.values().stream().mapToLong(Long::longValue).sum();
        return counts.entrySet().stream()
                .map(e -> DashboardRepartitionCycleDTO.builder()
                        .cycle(e.getKey())
                        .count(e.getValue())
                        .percentage(total == 0 ? 0d : (e.getValue() * 100.0 / total))
                        .build())
                .collect(Collectors.toList());
    }

    private List<DashboardJournalAuditDTO> buildJournalAudit() {
        return logRepository.findAll().stream()
                .sorted(Comparator.comparing(Log::getDateAction, Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .limit(5)
                .map(log -> {
                    String timeAgo = computeTimeAgo(log.getDateAction());
                    String user = log.getUtilisateurId() != null ? log.getUtilisateurId().toString() : "Système";
                    return DashboardJournalAuditDTO.builder()
                            .action(log.getAction() != null ? log.getAction().name() : null)
                            .reference(log.getEntiteId())
                            .timeAgo(timeAgo)
                            .user(user)
                            .build();
                })
                .collect(Collectors.toList());
    }

    private String computeTimeAgo(LocalDateTime date) {
        if (date == null) return null;
        Duration d = Duration.between(date, LocalDateTime.now());
        long minutes = d.toMinutes();
        if (minutes < 60) return "il y a " + minutes + " minutes";
        long hours = d.toHours();
        if (hours < 24) return "il y a " + hours + " heures";
        long days = d.toDays();
        return "il y a " + days + " jours";
    }
}
