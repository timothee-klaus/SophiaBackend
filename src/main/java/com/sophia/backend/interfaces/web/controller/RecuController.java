package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.InfoRecuDTO;
import com.sophia.backend.application.service.PaiementService;
import com.sophia.backend.domain.model.*;
import com.sophia.backend.domain.repository.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/recus")
@Tag(name = "Reçus", description = "Informations pour générer les reçus (frontend)")
public class RecuController {

    private final PaiementService paiementService;
    private final EleveRepository eleveRepository;
    private final InscriptionRepository inscriptionRepository;
    private final NiveauRepository niveauRepository;
    private final AnneeScolaireRepository anneeScolaireRepository;
    private final TranchePaiementRepository tranchePaiementRepository;
    private final FraisDiversRepository fraisDiversRepository;

    public RecuController(
            PaiementService paiementService,
            EleveRepository eleveRepository,
            InscriptionRepository inscriptionRepository,
            NiveauRepository niveauRepository,
            AnneeScolaireRepository anneeScolaireRepository,
            TranchePaiementRepository tranchePaiementRepository,
            FraisDiversRepository fraisDiversRepository) {
        this.paiementService = paiementService;
        this.eleveRepository = eleveRepository;
        this.inscriptionRepository = inscriptionRepository;
        this.niveauRepository = niveauRepository;
        this.anneeScolaireRepository = anneeScolaireRepository;
        this.tranchePaiementRepository = tranchePaiementRepository;
        this.fraisDiversRepository = fraisDiversRepository;
    }

    @GetMapping("/{paiementUuid}")
    @Operation(
        summary = "Obtenir les informations pour un reçu",
        description = "Récupère toutes les informations nécessaires pour générer un reçu de paiement.\n\n" +
            "Le frontend pourra utiliser ces données pour générer un PDF ou imprimer le reçu."
    )
    public ResponseEntity<InfoRecuDTO> getInfoRecu(@PathVariable UUID paiementUuid) {
        Paiement paiement = paiementService.findByUuid(paiementUuid)
                .orElseThrow(() -> new RuntimeException("Paiement non trouvé"));

        Inscription inscription = inscriptionRepository.findByUuid(paiement.getInscriptionUuid())
                .orElseThrow(() -> new RuntimeException("Inscription non trouvée"));

        Eleve eleve = eleveRepository.findById(inscription.getEleveUuid())
                .orElseThrow(() -> new RuntimeException("Élève non trouvé"));

        Niveau niveau = niveauRepository.findByUuid(inscription.getNiveauUuid())
                .orElseThrow(() -> new RuntimeException("Niveau non trouvé"));

        AnneeScolaire anneeScolaire = anneeScolaireRepository.findByUuid(inscription.getAnneeScolaireUuid())
                .orElseThrow(() -> new RuntimeException("Année scolaire non trouvée"));

        InfoRecuDTO info = new InfoRecuDTO();

        // Info paiement
        info.setPaiementUuid(paiement.getUuid());
        info.setMontant(paiement.getMontant());
        info.setDatePaiement(paiement.getDatePaiement());
        info.setModePaiement(paiement.getModePaiement());
        info.setTypePaiement(paiement.getTypePaiement());
        info.setCommentaire(paiement.getCommentaire());

        // Info élève
        info.setMatriculeEleve(eleve.getMatricule());
        info.setNomEleve(eleve.getNom());
        info.setPrenomEleve(eleve.getPrenom());

        // Info inscription
        info.setNiveau(niveau.getNom());
        info.setAnneeScolaire(anneeScolaire.getLibelle());

        // Info tranche ou frais selon le type
        if (paiement.getReferenceUuid() != null) {
            switch (paiement.getTypePaiement()) {
                case SCOLARITE:
                    tranchePaiementRepository.findByUuid(paiement.getReferenceUuid())
                            .ifPresent(tranche -> {
                                info.setNomTranche(tranche.getNomTranche());
                                info.setMontantTranche(tranche.getMontant());
                            });
                    break;
                case DIVERS:
                    fraisDiversRepository.findByUuid(paiement.getReferenceUuid())
                            .ifPresent(frais -> {
                                info.setLibelleFrais(frais.getDescription());
                                info.setMontantFrais(frais.getMontant());
                            });
                    break;
                case INSCRIPTION:
                    info.setLibelleFrais("Frais d'inscription");
                    info.setMontantFrais(paiement.getMontant());
                    break;
            }
        }

        return ResponseEntity.ok(info);
    }
}

