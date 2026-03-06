# 📋 Guide Rapide - Opérations par Service

## 🎯 Index des Services

| # | Service | Total Ops | CRUD | Métier |
|----|---------|-----------|------|--------|
| 1 | EtablissementService | 9 | 6 | 3 |
| 2 | CycleService | 6 | 5 | 1 |
| 3 | NiveauService | 8 | 7 | 1 |
| 4 | AnneeScolaireService | 9 | 6 | 3 |
| 5 | EleveService | 10 | 6 | 4 |
| 6 | InscriptionService | 11 | 6 | 5 |
| 7 | FraisScolaireService | 9 | 7 | 2 |
| 8 | TranchePaiementService | 8 | 6 | 1 |
| 9 | FraisDiversService | 9 | 7 | 2 |
| 10 | FraisInscriptionService | 8 | 7 | 1 |
| 11 | PaiementService | 14 | 6 | 8 |
| 12 | DocumentService | 9 | 6 | 2 |
| 13 | BlocageService | 10 | 7 | 3 |
| 14 | NotificationService | 12 | 7 | 4 |
| 15 | RecuService | 11 | 6 | 5 |
| 16 | UtilisateurService | 10 | 7 | 3 |
| 17 | AccesEtablissementService | 7 | 5 | 2 |
| 18 | LogService | 11 | 5 | 6 |

---

## 1️⃣ EtablissementService (9 ops)

**CRUD:**
- `findById(Long)` → Optional
- `findAll()` → List
- `findByStatut(String)` → List
- `create(Etablissement)` → Etablissement
- `update(Etablissement)` → Etablissement
- `delete(Long)` → void

**Métier:**
- `creerEtablissement(Etablissement)` → Etablissement
- `modifierEtablissement(Long, Etablissement)` → Etablissement
- `supprimerEtablissement(Long)` → void

---

## 2️⃣ CycleService (6 ops)

**CRUD:**
- `findById(Long)` → Optional
- `findAll()` → List
- `create(Cycle)` → Cycle
- `update(Cycle)` → Cycle
- `delete(Long)` → void

**Métier:**
- `obtenirCyclesDisponibles()` → List

---

## 3️⃣ NiveauService (8 ops)

**CRUD:**
- `findById(Long)` → Optional
- `findAll()` → List
- `findByCycleId(Long)` → List
- `findByEtablissementId(Long)` → List
- `create(Niveau)` → Niveau
- `update(Niveau)` → Niveau
- `delete(Long)` → void

**Métier:**
- `configurerNiveauxEtablissement(Long, Long)` → List

---

## 4️⃣ AnneeScolaireService (9 ops)

**CRUD:**
- `findById(Long)` → Optional
- `findAll()` → List
- `findActive()` → Optional
- `create(AnneeScolaire)` → AnneeScolaire
- `update(AnneeScolaire)` → AnneeScolaire
- `delete(Long)` → void

**Métier:**
- `ouvrirAnneeScolaire(AnneeScolaire)` → AnneeScolaire
- `cloturerAnneeScolaire(Long)` → void
- `obtenirAnneeScolaireActive()` → Optional

---

## 5️⃣ EleveService (10 ops)

**CRUD:**
- `findById(UUID)` → Optional
- `findByMatricule(String)` → Optional
- `findAll()` → List
- `create(Eleve)` → Eleve
- `update(Eleve)` → Eleve
- `delete(UUID)` → void

**Métier:**
- `creerDossierEleve(Eleve)` → Eleve
- `modifierEleve(UUID, Eleve)` → Eleve
- `archiverEleve(UUID)` → void
- `rechercherEleve(UUID)` → Optional

---

## 6️⃣ InscriptionService (11 ops)

**CRUD:**
- `findById(Long)` → Optional
- `findAll()` → List
- `findByEleveId(UUID)` → List
- `create(Inscription)` → Inscription
- `update(Inscription)` → Inscription
- `delete(Long)` → void

**Métier:**
- `enregistrerDossierInscription(Inscription)` → Inscription
- `validerPiecesInscription(Long)` → Inscription
- `enregistrerPaiementInscription(Long)` → Inscription
- `obtenirInscriptionsActives(UUID)` → List
- `terminerInscription(Long)` → void

---

## 7️⃣ FraisScolaireService (9 ops)

**CRUD:**
- `findById(Long)` → Optional
- `findAll()` → List
- `findByNiveauId(Long)` → List
- `findByAnneeScolaireId(Long)` → List
- `create(FraisScolaire)` → FraisScolaire
- `update(FraisScolaire)` → FraisScolaire
- `delete(Long)` → void

**Métier:**
- `definirFraisScolaire(Long, Long, FraisScolaire)` → FraisScolaire
- `obtenirFraisPourNiveauEtAnnee(Long, Long)` → Optional

---

## 8️⃣ TranchePaiementService (8 ops)

**CRUD:**
- `findById(Long)` → Optional
- `findAll()` → List
- `findByFraisScolaireId(Long)` → List
- `create(TranchePaiement)` → TranchePaiement
- `update(TranchePaiement)` → TranchePaiement
- `delete(Long)` → void

**Métier:**
- `visualiserEchéancierParFraisScolaire(Long)` → List

---

## 9️⃣ FraisDiversService (9 ops)

**CRUD:**
- `findById(Long)` → Optional
- `findAll()` → List
- `findByNiveauId(Long)` → List
- `findByAnneeScolaireId(Long)` → List
- `create(FraisDivers)` → FraisDivers
- `update(FraisDivers)` → FraisDivers
- `delete(Long)` → void

**Métier:**
- `definirFraisDivers(Long, Long, FraisDivers)` → FraisDivers
- `obtenirFraisDiversPourNiveau(Long)` → List

---

## 🔟 FraisInscriptionService (8 ops)

**CRUD:**
- `findById(Long)` → Optional
- `findAll()` → List
- `findByCycleId(Long)` → List
- `findByAnneeScolaireId(Long)` → List
- `create(FraisInscription)` → FraisInscription
- `update(FraisInscription)` → FraisInscription
- `delete(Long)` → void

**Métier:**
- `obtenirFraisInscriptionPourCycle(Long, Long)` → Optional

---

## 1️⃣1️⃣ PaiementService (14 ops) ⭐ CRITIQUE

**CRUD:**
- `findById(Long)` → Optional
- `findAll()` → List
- `findByInscriptionId(Long)` → List
- `create(Paiement)` → Paiement
- `update(Paiement)` → Paiement
- `delete(Long)` → void

**Métier:**
- `enregistrerPaiement(Paiement)` → Paiement
- `associerPaiementAInscription(Long, Paiement)` → Paiement
- `visualiserEchéancier(Long)` → List
- `calculerSoldeRestant(Long, BigDecimal)` → BigDecimal ⭐
- `obtenirHistoriquePaiements(Long)` → List
- `estAJour(Long, BigDecimal)` → boolean
- `obtenirPaiementsEnRetard(Long)` → List

---

## 1️⃣2️⃣ DocumentService (9 ops)

**CRUD:**
- `findById(Long)` → Optional
- `findAll()` → List
- `findByEleveId(UUID)` → List
- `create(Document)` → Document
- `update(Document)` → Document
- `delete(Long)` → void

**Métier:**
- `televersRecu(UUID, UUID, Document)` → Document
- `obtenirDocumentsEleve(UUID)` → List

---

## 1️⃣3️⃣ BlocageService (10 ops)

**CRUD:**
- `findById(Long)` → Optional
- `findAll()` → List
- `findByInscriptionId(Long)` → List
- `findActiveBlocks()` → List
- `create(Blocage)` → Blocage
- `update(Blocage)` → Blocage
- `delete(Long)` → void

**Métier:**
- `bloquerInscription(Long, String, String)` → Blocage
- `leverBlocage(Long, UUID)` → void
- `estBloquee(Long)` → boolean

---

## 1️⃣4️⃣ NotificationService (12 ops)

**CRUD:**
- `findById(Long)` → Optional
- `findAll()` → List
- `findByDestinataireId(UUID)` → List
- `findUnreadByDestinataireId(UUID)` → List
- `create(Notification)` → Notification
- `update(Notification)` → Notification
- `delete(Long)` → void

**Métier:**
- `notifierReçuDisponible(UUID, UUID, String)` → Notification ⭐
- `alerterImpayesCritiques(UUID, int)` → Notification ⭐
- `marquerCommeLue(Long)` → Notification
- `obtenirNotificationsNonLues(UUID)` → List

---

## 1️⃣5️⃣ RecuService (11 ops)

**CRUD:**
- `findById(Long)` → Optional
- `findAll()` → List
- `findByPaiementId(Long)` → List
- `create(Recu)` → Recu
- `update(Recu)` → Recu
- `delete(Long)` → void

**Métier:**
- `genererRecuInscription(Long, UUID)` → Recu
- `genererRecuPaiement(Long, UUID, String)` → Recu
- `demanderRecuNumerise(Long, UUID)` → Recu
- `traiterRecuDemande(Long, UUID, String)` → Recu
- `obtenirRecusParPaiement(Long)` → List

---

## 1️⃣6️⃣ UtilisateurService (10 ops)

**CRUD:**
- `findById(UUID)` → Optional
- `findByEmail(String)` → Optional
- `findAll()` → List
- `findByRole(String)` → List
- `create(Utilisateur)` → Utilisateur
- `update(Utilisateur)` → Utilisateur
- `delete(UUID)` → void

**Métier:**
- `authentifier(String, String)` → Optional ⭐
- `obtenirSecretaires()` → List
- `obtenirDirecteurs()` → List

---

## 1️⃣7️⃣ AccesEtablissementService (7 ops)

**CRUD:**
- `findById(Long)` → Optional
- `findByUtilisateurId(UUID)` → List
- `findByEtablissementId(Long)` → List
- `create(AccesEtablissement)` → AccesEtablissement
- `delete(Long)` → void

**Métier:**
- `accorderAcces(UUID, Long)` → AccesEtablissement
- `revoquerAcces(Long)` → void

---

## 1️⃣8️⃣ LogService (11 ops) ✓ AUDIT

**CRUD:**
- `findById(Long)` → Optional
- `findAll()` → List
- `findByUtilisateurId(UUID)` → List
- `create(Log)` → Log
- `delete(Long)` → void

**Métier:**
- `enregistrerCreation(UUID, String, String, String)` → void
- `enregistrerModification(UUID, String, String, String, String, String)` → void
- `enregistrerSuppression(UUID, String, String, String)` → void
- `enregistrerConnexion(UUID, String, String)` → void
- `enregistrerDeconnexion(UUID)` → void
- `obtenirHistoriquUtilisateur(UUID)` → List

---

## 🎯 Opérations Hautement Recommandées

### Pour Secrétaires
1. `InscriptionService.enregistrerDossierInscription()` - Démarrer
2. `InscriptionService.validerPiecesInscription()` - Valider
3. `PaiementService.enregistrerPaiement()` - Enregistrer paiement
4. `RecuService.genererRecuPaiement()` - Générer reçu
5. `BlocageService.bloquerInscription()` - Bloquer si nécessaire

### Pour Directeurs
1. `PaiementService.visualiserEchéancier()` - Voir l'état
2. `PaiementService.calculerSoldeRestant()` - Connaître le solde
3. `RecuService.demanderRecuNumerise()` - Demander reçu
4. `NotificationService.obtenirNotificationsNonLues()` - Consulter alertes
5. `EleveService.rechercherEleve()` - Chercher un élève

### Pour le Système
1. `PaiementService.calculerSoldeRestant()` - Calcul cœur
2. `NotificationService.notifierReçuDisponible()` - Push notifications
3. `NotificationService.alerterImpayesCritiques()` - Alerting
4. `LogService.enregistrerConnexion()` - Audit trail
5. `BlocageService.bloquerInscription()` - Business logic

---

## 📊 Légende

| Symbole | Signification |
|---------|--------------|
| ⭐ | Opération critique |
| ✓ | Audit/Sécurité |
| → | Type de retour |
| UUID | Identifiant unique |
| Optional | Peut être vide |
| List | Collection de résultats |
| void | Pas de retour |

---

Generated on: 6 March 2026

