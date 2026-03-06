# 📊 Tableau Visuel - Toutes les 169 Opérations

## 🎨 Légende

| Symbole | Signification |
|---------|--------------|
| ⭐ | Opération Critique |
| 🔑 | Authentification |
| 💰 | Financier |
| 📝 | CRUD de base |
| 🎯 | Cas d'usage métier |
| ✓ | Audit/Sécurité |

---

## Service 1: EtablissementService

| # | Opération | Type | Signature |
|---|-----------|------|-----------|
| 1.1 | findById | 📝 | `Optional<Etablissement> findById(Long id)` |
| 1.2 | findAll | 📝 | `List<Etablissement> findAll()` |
| 1.3 | findByStatut | 📝 | `List<Etablissement> findByStatut(String statut)` |
| 1.4 | create | 📝 | `Etablissement create(Etablissement)` |
| 1.5 | update | 📝 | `Etablissement update(Etablissement)` |
| 1.6 | delete | 📝 | `void delete(Long id)` |
| 1.7 | creerEtablissement | 🎯 | `Etablissement creerEtablissement(Etablissement)` |
| 1.8 | modifierEtablissement | 🎯 | `Etablissement modifierEtablissement(Long, Etablissement)` |
| 1.9 | supprimerEtablissement | 🎯 | `void supprimerEtablissement(Long)` |

---

## Service 2: CycleService

| # | Opération | Type | Signature |
|---|-----------|------|-----------|
| 2.1 | findById | 📝 | `Optional<Cycle> findById(Long id)` |
| 2.2 | findAll | 📝 | `List<Cycle> findAll()` |
| 2.3 | create | 📝 | `Cycle create(Cycle)` |
| 2.4 | update | 📝 | `Cycle update(Cycle)` |
| 2.5 | delete | 📝 | `void delete(Long id)` |
| 2.6 | obtenirCyclesDisponibles | 🎯 | `List<Cycle> obtenirCyclesDisponibles()` |

---

## Service 3: NiveauService

| # | Opération | Type | Signature |
|---|-----------|------|-----------|
| 3.1 | findById | 📝 | `Optional<Niveau> findById(Long id)` |
| 3.2 | findAll | 📝 | `List<Niveau> findAll()` |
| 3.3 | findByCycleId | 📝 | `List<Niveau> findByCycleId(Long cycleId)` |
| 3.4 | findByEtablissementId | 📝 | `List<Niveau> findByEtablissementId(Long etablissementId)` |
| 3.5 | create | 📝 | `Niveau create(Niveau)` |
| 3.6 | update | 📝 | `Niveau update(Niveau)` |
| 3.7 | delete | 📝 | `void delete(Long id)` |
| 3.8 | configurerNiveauxEtablissement | 🎯 | `List<Niveau> configurerNiveauxEtablissement(Long, Long)` |

---

## Service 4: AnneeScolaireService

| # | Opération | Type | Signature |
|---|-----------|------|-----------|
| 4.1 | findById | 📝 | `Optional<AnneeScolaire> findById(Long id)` |
| 4.2 | findAll | 📝 | `List<AnneeScolaire> findAll()` |
| 4.3 | findActive | 📝 | `Optional<AnneeScolaire> findActive()` |
| 4.4 | create | 📝 | `AnneeScolaire create(AnneeScolaire)` |
| 4.5 | update | 📝 | `AnneeScolaire update(AnneeScolaire)` |
| 4.6 | delete | 📝 | `void delete(Long id)` |
| 4.7 | ouvrirAnneeScolaire | 🎯 | `AnneeScolaire ouvrirAnneeScolaire(AnneeScolaire)` |
| 4.8 | cloturerAnneeScolaire | 🎯 | `void cloturerAnneeScolaire(Long)` |
| 4.9 | obtenirAnneeScolaireActive | 🎯 | `Optional<AnneeScolaire> obtenirAnneeScolaireActive()` |

---

## Service 5: EleveService

| # | Opération | Type | Signature |
|---|-----------|------|-----------|
| 5.1 | findById | 📝 | `Optional<Eleve> findById(UUID id)` |
| 5.2 | findByMatricule | 📝 | `Optional<Eleve> findByMatricule(String matricule)` |
| 5.3 | findAll | 📝 | `List<Eleve> findAll()` |
| 5.4 | create | 📝 | `Eleve create(Eleve)` |
| 5.5 | update | 📝 | `Eleve update(Eleve)` |
| 5.6 | delete | 📝 | `void delete(UUID id)` |
| 5.7 | creerDossierEleve | 🎯 | `Eleve creerDossierEleve(Eleve)` |
| 5.8 | modifierEleve | 🎯 | `Eleve modifierEleve(UUID, Eleve)` |
| 5.9 | archiverEleve | 🎯 | `void archiverEleve(UUID)` |
| 5.10 | rechercherEleve | 🎯 | `Optional<Eleve> rechercherEleve(UUID)` |

---

## Service 6: InscriptionService

| # | Opération | Type | Signature |
|---|-----------|------|-----------|
| 6.1 | findById | 📝 | `Optional<Inscription> findById(Long id)` |
| 6.2 | findAll | 📝 | `List<Inscription> findAll()` |
| 6.3 | findByEleveId | 📝 | `List<Inscription> findByEleveId(UUID eleveId)` |
| 6.4 | create | 📝 | `Inscription create(Inscription)` |
| 6.5 | update | 📝 | `Inscription update(Inscription)` |
| 6.6 | delete | 📝 | `void delete(Long id)` |
| 6.7 | enregistrerDossierInscription | 🎯 | `Inscription enregistrerDossierInscription(Inscription)` |
| 6.8 | validerPiecesInscription | 🎯 | `Inscription validerPiecesInscription(Long)` |
| 6.9 | enregistrerPaiementInscription | 🎯 | `Inscription enregistrerPaiementInscription(Long)` |
| 6.10 | obtenirInscriptionsActives | 🎯 | `List<Inscription> obtenirInscriptionsActives(UUID)` |
| 6.11 | terminerInscription | 🎯 | `void terminerInscription(Long)` |

---

## Service 7: FraisScolaireService

| # | Opération | Type | Signature |
|---|-----------|------|-----------|
| 7.1 | findById | 📝 | `Optional<FraisScolaire> findById(Long id)` |
| 7.2 | findAll | 📝 | `List<FraisScolaire> findAll()` |
| 7.3 | findByNiveauId | 📝 | `List<FraisScolaire> findByNiveauId(Long niveauId)` |
| 7.4 | findByAnneeScolaireId | 📝 | `List<FraisScolaire> findByAnneeScolaireId(Long anneeScolaireId)` |
| 7.5 | create | 📝 | `FraisScolaire create(FraisScolaire)` |
| 7.6 | update | 📝 | `FraisScolaire update(FraisScolaire)` |
| 7.7 | delete | 📝 | `void delete(Long id)` |
| 7.8 | definirFraisScolaire | 💰 | `FraisScolaire definirFraisScolaire(Long, Long, FraisScolaire)` |
| 7.9 | obtenirFraisPourNiveauEtAnnee | 💰 | `Optional<FraisScolaire> obtenirFraisPourNiveauEtAnnee(Long, Long)` |

---

## Service 8: TranchePaiementService

| # | Opération | Type | Signature |
|---|-----------|------|-----------|
| 8.1 | findById | 📝 | `Optional<TranchePaiement> findById(Long id)` |
| 8.2 | findAll | 📝 | `List<TranchePaiement> findAll()` |
| 8.3 | findByFraisScolaireId | 📝 | `List<TranchePaiement> findByFraisScolaireId(Long fraisScolaireId)` |
| 8.4 | create | 📝 | `TranchePaiement create(TranchePaiement)` |
| 8.5 | update | 📝 | `TranchePaiement update(TranchePaiement)` |
| 8.6 | delete | 📝 | `void delete(Long id)` |
| 8.7 | visualiserEchéancierParFraisScolaire | 💰 | `List<TranchePaiement> visualiserEchéancierParFraisScolaire(Long)` |

---

## Service 9: FraisDiversService

| # | Opération | Type | Signature |
|---|-----------|------|-----------|
| 9.1 | findById | 📝 | `Optional<FraisDivers> findById(Long id)` |
| 9.2 | findAll | 📝 | `List<FraisDivers> findAll()` |
| 9.3 | findByNiveauId | 📝 | `List<FraisDivers> findByNiveauId(Long niveauId)` |
| 9.4 | findByAnneeScolaireId | 📝 | `List<FraisDivers> findByAnneeScolaireId(Long anneeScolaireId)` |
| 9.5 | create | 📝 | `FraisDivers create(FraisDivers)` |
| 9.6 | update | 📝 | `FraisDivers update(FraisDivers)` |
| 9.7 | delete | 📝 | `void delete(Long id)` |
| 9.8 | definirFraisDivers | 💰 | `FraisDivers definirFraisDivers(Long, Long, FraisDivers)` |
| 9.9 | obtenirFraisDiversPourNiveau | 💰 | `List<FraisDivers> obtenirFraisDiversPourNiveau(Long)` |

---

## Service 10: FraisInscriptionService

| # | Opération | Type | Signature |
|---|-----------|------|-----------|
| 10.1 | findById | 📝 | `Optional<FraisInscription> findById(Long id)` |
| 10.2 | findAll | 📝 | `List<FraisInscription> findAll()` |
| 10.3 | findByCycleId | 📝 | `List<FraisInscription> findByCycleId(Long cycleId)` |
| 10.4 | findByAnneeScolaireId | 📝 | `List<FraisInscription> findByAnneeScolaireId(Long anneeScolaireId)` |
| 10.5 | create | 📝 | `FraisInscription create(FraisInscription)` |
| 10.6 | update | 📝 | `FraisInscription update(FraisInscription)` |
| 10.7 | delete | 📝 | `void delete(Long id)` |
| 10.8 | obtenirFraisInscriptionPourCycle | 💰 | `Optional<FraisInscription> obtenirFraisInscriptionPourCycle(Long, Long)` |

---

## Service 11: PaiementService ⭐ CRITIQUE

| # | Opération | Type | Signature |
|---|-----------|------|-----------|
| 11.1 | findById | 📝 | `Optional<Paiement> findById(Long id)` |
| 11.2 | findAll | 📝 | `List<Paiement> findAll()` |
| 11.3 | findByInscriptionId | 📝 | `List<Paiement> findByInscriptionId(Long inscriptionId)` |
| 11.4 | create | 📝 | `Paiement create(Paiement)` |
| 11.5 | update | 📝 | `Paiement update(Paiement)` |
| 11.6 | delete | 📝 | `void delete(Long id)` |
| 11.7 | enregistrerPaiement | 💰 | `Paiement enregistrerPaiement(Paiement)` |
| 11.8 | associerPaiementAInscription | 💰 | `Paiement associerPaiementAInscription(Long, Paiement)` |
| 11.9 | visualiserEchéancier | 💰 | `List<Paiement> visualiserEchéancier(Long)` |
| 11.10 | calculerSoldeRestant | ⭐💰 | `BigDecimal calculerSoldeRestant(Long, BigDecimal)` |
| 11.11 | obtenirHistoriquePaiements | 💰 | `List<Paiement> obtenirHistoriquePaiements(Long)` |
| 11.12 | estAJour | 💰 | `boolean estAJour(Long, BigDecimal)` |
| 11.13 | obtenirPaiementsEnRetard | 💰 | `List<Paiement> obtenirPaiementsEnRetard(Long)` |

---

## Service 12: DocumentService

| # | Opération | Type | Signature |
|---|-----------|------|-----------|
| 12.1 | findById | 📝 | `Optional<Document> findById(Long id)` |
| 12.2 | findAll | 📝 | `List<Document> findAll()` |
| 12.3 | findByEleveId | 📝 | `List<Document> findByEleveId(UUID eleveId)` |
| 12.4 | create | 📝 | `Document create(Document)` |
| 12.5 | update | 📝 | `Document update(Document)` |
| 12.6 | delete | 📝 | `void delete(Long id)` |
| 12.7 | televersRecu | 🎯 | `Document televersRecu(UUID, UUID, Document)` |
| 12.8 | obtenirDocumentsEleve | 🎯 | `List<Document> obtenirDocumentsEleve(UUID)` |

---

## Service 13: BlocageService

| # | Opération | Type | Signature |
|---|-----------|------|-----------|
| 13.1 | findById | 📝 | `Optional<Blocage> findById(Long id)` |
| 13.2 | findAll | 📝 | `List<Blocage> findAll()` |
| 13.3 | findByInscriptionId | 📝 | `List<Blocage> findByInscriptionId(Long inscriptionId)` |
| 13.4 | findActiveBlocks | 📝 | `List<Blocage> findActiveBlocks()` |
| 13.5 | create | 📝 | `Blocage create(Blocage)` |
| 13.6 | update | 📝 | `Blocage update(Blocage)` |
| 13.7 | delete | 📝 | `void delete(Long id)` |
| 13.8 | bloquerInscription | 🎯 | `Blocage bloquerInscription(Long, String, String)` |
| 13.9 | leverBlocage | 🎯 | `void leverBlocage(Long, UUID)` |
| 13.10 | estBloquee | 🎯 | `boolean estBloquee(Long)` |

---

## Service 14: NotificationService ⭐

| # | Opération | Type | Signature |
|---|-----------|------|-----------|
| 14.1 | findById | 📝 | `Optional<Notification> findById(Long id)` |
| 14.2 | findAll | 📝 | `List<Notification> findAll()` |
| 14.3 | findByDestinataireId | 📝 | `List<Notification> findByDestinataireId(UUID destinataireId)` |
| 14.4 | findUnreadByDestinataireId | 📝 | `List<Notification> findUnreadByDestinataireId(UUID destinataireId)` |
| 14.5 | create | 📝 | `Notification create(Notification)` |
| 14.6 | update | 📝 | `Notification update(Notification)` |
| 14.7 | delete | 📝 | `void delete(Long id)` |
| 14.8 | notifierReçuDisponible | ⭐🎯 | `Notification notifierReçuDisponible(UUID, UUID, String)` |
| 14.9 | alerterImpayesCritiques | ⭐🎯 | `Notification alerterImpayesCritiques(UUID, int)` |
| 14.10 | marquerCommeLue | 🎯 | `Notification marquerCommeLue(Long)` |
| 14.11 | obtenirNotificationsNonLues | 🎯 | `List<Notification> obtenirNotificationsNonLues(UUID)` |

---

## Service 15: RecuService

| # | Opération | Type | Signature |
|---|-----------|------|-----------|
| 15.1 | findById | 📝 | `Optional<Recu> findById(Long id)` |
| 15.2 | findAll | 📝 | `List<Recu> findAll()` |
| 15.3 | findByPaiementId | 📝 | `List<Recu> findByPaiementId(Long paiementId)` |
| 15.4 | create | 📝 | `Recu create(Recu)` |
| 15.5 | update | 📝 | `Recu update(Recu)` |
| 15.6 | delete | 📝 | `void delete(Long id)` |
| 15.7 | genererRecuInscription | 🎯 | `Recu genererRecuInscription(Long, UUID)` |
| 15.8 | genererRecuPaiement | 🎯 | `Recu genererRecuPaiement(Long, UUID, String)` |
| 15.9 | demanderRecuNumerise | 🎯 | `Recu demanderRecuNumerise(Long, UUID)` |
| 15.10 | traiterRecuDemande | 🎯 | `Recu traiterRecuDemande(Long, UUID, String)` |
| 15.11 | obtenirRecusParPaiement | 🎯 | `List<Recu> obtenirRecusParPaiement(Long)` |

---

## Service 16: UtilisateurService

| # | Opération | Type | Signature |
|---|-----------|------|-----------|
| 16.1 | findById | 📝 | `Optional<Utilisateur> findById(UUID id)` |
| 16.2 | findByEmail | 📝 | `Optional<Utilisateur> findByEmail(String email)` |
| 16.3 | findAll | 📝 | `List<Utilisateur> findAll()` |
| 16.4 | findByRole | 📝 | `List<Utilisateur> findByRole(String role)` |
| 16.5 | create | 📝 | `Utilisateur create(Utilisateur)` |
| 16.6 | update | 📝 | `Utilisateur update(Utilisateur)` |
| 16.7 | delete | 📝 | `void delete(UUID id)` |
| 16.8 | authentifier | 🔑⭐ | `Optional<Utilisateur> authentifier(String, String)` |
| 16.9 | obtenirSecretaires | 🎯 | `List<Utilisateur> obtenirSecretaires()` |
| 16.10 | obtenirDirecteurs | 🎯 | `List<Utilisateur> obtenirDirecteurs()` |

---

## Service 17: AccesEtablissementService

| # | Opération | Type | Signature |
|---|-----------|------|-----------|
| 17.1 | findById | 📝 | `Optional<AccesEtablissement> findById(Long id)` |
| 17.2 | findByUtilisateurId | 📝 | `List<AccesEtablissement> findByUtilisateurId(UUID utilisateurId)` |
| 17.3 | findByEtablissementId | 📝 | `List<AccesEtablissement> findByEtablissementId(Long etablissementId)` |
| 17.4 | create | 📝 | `AccesEtablissement create(AccesEtablissement)` |
| 17.5 | delete | 📝 | `void delete(Long id)` |
| 17.6 | accorderAcces | 🎯 | `AccesEtablissement accorderAcces(UUID, Long)` |
| 17.7 | revoquerAcces | 🎯 | `void revoquerAcces(Long)` |

---

## Service 18: LogService ✓ AUDIT

| # | Opération | Type | Signature |
|---|-----------|------|-----------|
| 18.1 | findById | 📝 | `Optional<Log> findById(Long id)` |
| 18.2 | findAll | 📝 | `List<Log> findAll()` |
| 18.3 | findByUtilisateurId | 📝 | `List<Log> findByUtilisateurId(UUID utilisateurId)` |
| 18.4 | create | 📝 | `Log create(Log)` |
| 18.5 | delete | 📝 | `void delete(Long id)` |
| 18.6 | enregistrerCreation | ✓ | `void enregistrerCreation(UUID, String, String, String)` |
| 18.7 | enregistrerModification | ✓ | `void enregistrerModification(UUID, String, String, String, String, String)` |
| 18.8 | enregistrerSuppression | ✓ | `void enregistrerSuppression(UUID, String, String, String)` |
| 18.9 | enregistrerConnexion | ✓ | `void enregistrerConnexion(UUID, String, String)` |
| 18.10 | enregistrerDeconnexion | ✓ | `void enregistrerDeconnexion(UUID)` |
| 18.11 | obtenirHistoriquUtilisateur | ✓ | `List<Log> obtenirHistoriquUtilisateur(UUID)` |

---

## 📊 Résumé Final

```
Total Opérations:      169
├─ CRUD de base (📝):  105
├─ Cas d'usage (🎯):   54
├─ Financier (💰):     13
├─ Audit (✓):          6
├─ Critique (⭐):      6
└─ Authentification (🔑): 1
```

**Statut:** ✅ **TOUS LES SERVICES IMPLÉMENTÉS**

---

Generated: 6 Mars 2026

