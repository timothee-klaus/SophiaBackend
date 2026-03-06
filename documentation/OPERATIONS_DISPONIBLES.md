# 📋 Opérations Disponibles - Sophia Backend

## 🎯 Vue d'ensemble
Ce document liste toutes les opérations disponibles dans le système Sophia Backend, organisées par service et par cas d'usage.

---

## 1️⃣ **EtablissementService** - Gestion des Établissements

### Opérations CRUD de base
- `Optional<Etablissement> findById(Long id)` - Rechercher un établissement par ID
- `List<Etablissement> findAll()` - Récupérer tous les établissements
- `List<Etablissement> findByStatut(String statut)` - Filtrer par statut (ACTIF, INACTIF)
- `Etablissement create(Etablissement)` - Créer un établissement
- `Etablissement update(Etablissement)` - Mettre à jour un établissement
- `void delete(Long id)` - Supprimer un établissement

### Opérations métier
- `Etablissement creerEtablissement(Etablissement)` - Créer un nouvel établissement
- `Etablissement modifierEtablissement(Long id, Etablissement)` - Modifier les infos d'un établissement
- `void supprimerEtablissement(Long id)` - Supprimer un établissement

---

## 2️⃣ **CycleService** - Gestion des Cycles

### Opérations CRUD de base
- `Optional<Cycle> findById(Long id)` - Rechercher un cycle
- `List<Cycle> findAll()` - Récupérer tous les cycles
- `Cycle create(Cycle)` - Créer un cycle
- `Cycle update(Cycle)` - Mettre à jour un cycle
- `void delete(Long id)` - Supprimer un cycle

### Opérations métier
- `List<Cycle> obtenirCyclesDisponibles()` - Récupérer les cycles disponibles (Préscolaire, Primaire, Collège, Lycée)

---

## 3️⃣ **NiveauService** - Gestion des Niveaux

### Opérations CRUD de base
- `Optional<Niveau> findById(Long id)` - Rechercher un niveau
- `List<Niveau> findAll()` - Récupérer tous les niveaux
- `List<Niveau> findByCycleId(Long cycleId)` - Trouver les niveaux d'un cycle
- `List<Niveau> findByEtablissementId(Long etablissementId)` - Trouver les niveaux d'un établissement
- `Niveau create(Niveau)` - Créer un niveau
- `Niveau update(Niveau)` - Mettre à jour un niveau
- `void delete(Long id)` - Supprimer un niveau

### Opérations métier
- `List<Niveau> configurerNiveauxEtablissement(Long etablissementId, Long cycleId)` - Configurer les cycles/niveaux d'un établissement

---

## 4️⃣ **AnneeScolaireService** - Gestion de l'Année Scolaire

### Opérations CRUD de base
- `Optional<AnneeScolaire> findById(Long id)` - Rechercher une année
- `List<AnneeScolaire> findAll()` - Récupérer toutes les années
- `Optional<AnneeScolaire> findActive()` - Trouver l'année active
- `AnneeScolaire create(AnneeScolaire)` - Créer une année
- `AnneeScolaire update(AnneeScolaire)` - Mettre à jour une année
- `void delete(Long id)` - Supprimer une année

### Opérations métier
- `AnneeScolaire ouvrirAnneeScolaire(AnneeScolaire)` - Ouvrir une nouvelle année scolaire
- `void cloturerAnneeScolaire(Long anneeScolaireId)` - Clôturer l'année scolaire active
- `Optional<AnneeScolaire> obtenirAnneeScolaireActive()` - Obtenir l'année active

---

## 5️⃣ **EleveService** - Gestion des Élèves

### Opérations CRUD de base
- `Optional<Eleve> findById(UUID id)` - Rechercher un élève
- `Optional<Eleve> findByMatricule(String matricule)` - Rechercher par matricule
- `List<Eleve> findAll()` - Récupérer tous les élèves
- `Eleve create(Eleve)` - Créer un élève
- `Eleve update(Eleve)` - Mettre à jour un élève
- `void delete(UUID id)` - Supprimer un élève

### Opérations métier
- `Eleve creerDossierEleve(Eleve)` - Créer un nouveau dossier élève
- `Eleve modifierEleve(UUID id, Eleve)` - Consulter/modifier les informations d'un élève
- `void archiverEleve(UUID id)` - Archiver un élève (départ, fin de cycle)
- `Optional<Eleve> rechercherEleve(UUID id)` - Rechercher un élève et consulter sa fiche

---

## 6️⃣ **InscriptionService** - Gestion des Inscriptions

### Opérations CRUD de base
- `Optional<Inscription> findById(Long id)` - Rechercher une inscription
- `List<Inscription> findAll()` - Récupérer toutes les inscriptions
- `List<Inscription> findByEleveId(UUID eleveId)` - Trouver les inscriptions d'un élève
- `Inscription create(Inscription)` - Créer une inscription
- `Inscription update(Inscription)` - Mettre à jour une inscription
- `void delete(Long id)` - Supprimer une inscription

### Opérations métier
- `Inscription enregistrerDossierInscription(Inscription)` - Enregistrer le dépôt d'un dossier
- `Inscription validerPiecesInscription(Long inscriptionId)` - Valider les pièces fournies
- `Inscription enregistrerPaiementInscription(Long inscriptionId)` - Enregistrer le paiement des frais
- `List<Inscription> obtenirInscriptionsActives(UUID eleveId)` - Obtenir les inscriptions actives
- `void terminerInscription(Long inscriptionId)` - Marquer une inscription comme terminée

---

## 7️⃣ **FraisScolaireService** - Gestion des Frais de Scolarité

### Opérations CRUD de base
- `Optional<FraisScolaire> findById(Long id)` - Rechercher des frais
- `List<FraisScolaire> findAll()` - Récupérer tous les frais
- `List<FraisScolaire> findByNiveauId(Long niveauId)` - Trouver par niveau
- `List<FraisScolaire> findByAnneeScolaireId(Long anneeScolaireId)` - Trouver par année
- `FraisScolaire create(FraisScolaire)` - Créer des frais
- `FraisScolaire update(FraisScolaire)` - Mettre à jour des frais
- `void delete(Long id)` - Supprimer des frais

### Opérations métier
- `FraisScolaire definirFraisScolaire(Long niveauId, Long anneeScolaireId, FraisScolaire)` - Définir les frais par niveau et année
- `Optional<FraisScolaire> obtenirFraisPourNiveauEtAnnee(Long niveauId, Long anneeScolaireId)` - Obtenir les frais pour un niveau/année

---

## 8️⃣ **TranchePaiementService** - Gestion des Tranches de Paiement

### Opérations CRUD de base
- `Optional<TranchePaiement> findById(Long id)` - Rechercher une tranche
- `List<TranchePaiement> findAll()` - Récupérer toutes les tranches
- `List<TranchePaiement> findByFraisScolaireId(Long fraisScolaireId)` - Trouver par frais
- `TranchePaiement create(TranchePaiement)` - Créer une tranche
- `TranchePaiement update(TranchePaiement)` - Mettre à jour une tranche
- `void delete(Long id)` - Supprimer une tranche

### Opérations métier
- `List<TranchePaiement> visualiserEchéancierParFraisScolaire(Long fraisScolaireId)` - Visualiser l'échéancier

---

## 9️⃣ **FraisDiversService** - Gestion des Frais Divers

### Opérations CRUD de base
- `Optional<FraisDivers> findById(Long id)` - Rechercher des frais divers
- `List<FraisDivers> findAll()` - Récupérer tous les frais divers
- `List<FraisDivers> findByNiveauId(Long niveauId)` - Trouver par niveau
- `List<FraisDivers> findByAnneeScolaireId(Long anneeScolaireId)` - Trouver par année
- `FraisDivers create(FraisDivers)` - Créer des frais divers
- `FraisDivers update(FraisDivers)` - Mettre à jour des frais divers
- `void delete(Long id)` - Supprimer des frais divers

### Opérations métier
- `FraisDivers definirFraisDivers(Long niveauId, Long anneeScolaireId, FraisDivers)` - Définir les frais divers (ex: 3000 F pour examen)
- `List<FraisDivers> obtenirFraisDiversPourNiveau(Long niveauId)` - Obtenir les frais divers pour un niveau

---

## 🔟 **FraisInscriptionService** - Gestion des Frais d'Inscription

### Opérations CRUD de base
- `Optional<FraisInscription> findById(Long id)` - Rechercher des frais d'inscription
- `List<FraisInscription> findAll()` - Récupérer tous les frais d'inscription
- `List<FraisInscription> findByCycleId(Long cycleId)` - Trouver par cycle
- `List<FraisInscription> findByAnneeScolaireId(Long anneeScolaireId)` - Trouver par année
- `FraisInscription create(FraisInscription)` - Créer des frais d'inscription
- `FraisInscription update(FraisInscription)` - Mettre à jour des frais
- `void delete(Long id)` - Supprimer des frais d'inscription

### Opérations métier
- `Optional<FraisInscription> obtenirFraisInscriptionPourCycle(Long cycleId, Long anneeScolaireId)` - Obtenir les frais pour un cycle/année

---

## 1️⃣1️⃣ **PaiementService** - Gestion des Paiements

### Opérations CRUD de base
- `Optional<Paiement> findById(Long id)` - Rechercher un paiement
- `List<Paiement> findAll()` - Récupérer tous les paiements
- `List<Paiement> findByInscriptionId(Long inscriptionId)` - Trouver par inscription
- `Paiement create(Paiement)` - Créer un paiement
- `Paiement update(Paiement)` - Mettre à jour un paiement
- `void delete(Long id)` - Supprimer un paiement

### Opérations métier
- `Paiement enregistrerPaiement(Paiement)` - Enregistrer un paiement (avec tranche)
- `Paiement associerPaiementAInscription(Long inscriptionId, Paiement)` - Associer un paiement à une inscription
- `List<Paiement> visualiserEchéancier(Long inscriptionId)` - Visualiser l'échéancier (soldes par tranche)
- `BigDecimal calculerSoldeRestant(Long inscriptionId, BigDecimal montantTotal)` - **Calculer automatiquement le solde restant dû**
- `List<Paiement> obtenirHistoriquePaiements(Long inscriptionId)` - Consulter l'historique des paiements
- `boolean estAJour(Long inscriptionId, BigDecimal montantTotal)` - Vérifier si le paiement est à jour
- `List<Paiement> obtenirPaiementsEnRetard(Long inscriptionId)` - Obtenir les paiements en retard

---

## 1️⃣2️⃣ **DocumentService** - Gestion des Documents

### Opérations CRUD de base
- `Optional<Document> findById(Long id)` - Rechercher un document
- `List<Document> findAll()` - Récupérer tous les documents
- `List<Document> findByEleveId(UUID eleveId)` - Trouver les documents d'un élève
- `Document create(Document)` - Créer un document
- `Document update(Document)` - Mettre à jour un document
- `void delete(Long id)` - Supprimer un document

### Opérations métier
- `Document televersRecu(UUID eleveId, UUID utilisateurId, Document)` - Téléverser un reçu déjà signé
- `List<Document> obtenirDocumentsEleve(UUID eleveId)` - Obtenir tous les documents d'un élève

---

## 1️⃣3️⃣ **BlocageService** - Gestion des Blocages

### Opérations CRUD de base
- `Optional<Blocage> findById(Long id)` - Rechercher un blocage
- `List<Blocage> findAll()` - Récupérer tous les blocages
- `List<Blocage> findByInscriptionId(Long inscriptionId)` - Trouver par inscription
- `List<Blocage> findActiveBlocks()` - Trouver les blocages actifs
- `Blocage create(Blocage)` - Créer un blocage
- `Blocage update(Blocage)` - Mettre à jour un blocage
- `void delete(Long id)` - Supprimer un blocage

### Opérations métier
- `Blocage bloquerInscription(Long inscriptionId, String typeBlocage, String raison)` - Marquer comme "non autorisé à composer"
- `void leverBlocage(Long blocageId, UUID utilisateurId)` - Lever le blocage après régularisation
- `boolean estBloquee(Long inscriptionId)` - Vérifier si une inscription est bloquée

---

## 1️⃣4️⃣ **NotificationService** - Gestion des Notifications

### Opérations CRUD de base
- `Optional<Notification> findById(Long id)` - Rechercher une notification
- `List<Notification> findAll()` - Récupérer toutes les notifications
- `List<Notification> findByDestinataireId(UUID destinataireId)` - Trouver par destinataire
- `List<Notification> findUnreadByDestinataireId(UUID destinataireId)` - Trouver les non lues
- `Notification create(Notification)` - Créer une notification
- `Notification update(Notification)` - Mettre à jour une notification
- `void delete(Long id)` - Supprimer une notification

### Opérations métier
- `Notification notifierReçuDisponible(UUID directeurId, UUID secretaireId, String lien)` - **Notifier le directeur lors d'un nouveau reçu**
- `Notification alerterImpayesCritiques(UUID directeurId, int nombreImpayés)` - **Alerter en cas d'impayés critiques**
- `Notification marquerCommeLue(Long notificationId)` - Marquer une notification comme lue
- `List<Notification> obtenirNotificationsNonLues(UUID utilisateurId)` - Obtenir les notifications non lues

---

## 1️⃣5️⃣ **RecuService** - Gestion des Reçus

### Opérations CRUD de base
- `Optional<Recu> findById(Long id)` - Rechercher un reçu
- `List<Recu> findAll()` - Récupérer tous les reçus
- `List<Recu> findByPaiementId(Long paiementId)` - Trouver par paiement
- `Recu create(Recu)` - Créer un reçu
- `Recu update(Recu)` - Mettre à jour un reçu
- `void delete(Long id)` - Supprimer un reçu

### Opérations métier
- `Recu genererRecuInscription(Long paiementId, UUID secretaireId)` - Générer un reçu d'inscription
- `Recu genererRecuPaiement(Long paiementId, UUID secretaireId, String cheminFichier)` - Générer un reçu de paiement (PDF)
- `Recu demanderRecuNumerise(Long paiementId, UUID directeurId)` - Demander la numérisation d'un reçu
- `Recu traiterRecuDemande(Long recuId, UUID secretaireId, String cheminFichier)` - Traiter une demande de reçu
- `List<Recu> obtenirRecusParPaiement(Long paiementId)` - Obtenir les reçus d'un paiement

---

## 1️⃣6️⃣ **UtilisateurService** - Gestion des Utilisateurs

### Opérations CRUD de base
- `Optional<Utilisateur> findById(UUID id)` - Rechercher un utilisateur
- `Optional<Utilisateur> findByEmail(String email)` - Rechercher par email
- `List<Utilisateur> findAll()` - Récupérer tous les utilisateurs
- `List<Utilisateur> findByRole(String role)` - Trouver par rôle
- `Utilisateur create(Utilisateur)` - Créer un utilisateur
- `Utilisateur update(Utilisateur)` - Mettre à jour un utilisateur
- `void delete(UUID id)` - Supprimer un utilisateur

### Opérations métier
- `Optional<Utilisateur> authentifier(String email, String motDePasse)` - **Authentifier les utilisateurs via JWT**
- `List<Utilisateur> obtenirSecretaires()` - Récupérer tous les secrétaires
- `List<Utilisateur> obtenirDirecteurs()` - Récupérer tous les directeurs

---

## 1️⃣7️⃣ **AccesEtablissementService** - Gestion des Accès

### Opérations CRUD de base
- `Optional<AccesEtablissement> findById(Long id)` - Rechercher un accès
- `List<AccesEtablissement> findByUtilisateurId(UUID utilisateurId)` - Trouver par utilisateur
- `List<AccesEtablissement> findByEtablissementId(Long etablissementId)` - Trouver par établissement
- `AccesEtablissement create(AccesEtablissement)` - Créer un accès
- `void delete(Long id)` - Supprimer un accès

### Opérations métier
- `AccesEtablissement accorderAcces(UUID utilisateurId, Long etablissementId)` - Accorder l'accès à un établissement
- `void revoquerAcces(Long id)` - Révoquer l'accès à un établissement

---

## 1️⃣8️⃣ **LogService** - Gestion des Logs d'Audit

### Opérations CRUD de base
- `Optional<Log> findById(Long id)` - Rechercher un log
- `List<Log> findAll()` - Récupérer tous les logs
- `List<Log> findByUtilisateurId(UUID utilisateurId)` - Trouver par utilisateur
- `Log create(Log)` - Créer un log
- `void delete(Long id)` - Supprimer un log

### Opérations métier
- `void enregistrerCreation(UUID utilisateurId, String entite, String entiteId, String description)` - Enregistrer une création
- `void enregistrerModification(UUID utilisateurId, String entite, String entiteId, String anciennes_valeurs, String nouvelles_valeurs, String description)` - Enregistrer une modification
- `void enregistrerSuppression(UUID utilisateurId, String entite, String entiteId, String description)` - Enregistrer une suppression
- `void enregistrerConnexion(UUID utilisateurId, String adresseIp, String userAgent)` - Enregistrer une connexion
- `void enregistrerDeconnexion(UUID utilisateurId)` - Enregistrer une déconnexion
- `List<Log> obtenirHistoriquUtilisateur(UUID utilisateurId)` - Obtenir l'historique d'un utilisateur

---

## 📊 Résumé des Opérations

| Service | Total Operations | Opérations CRUD | Opérations Métier |
|---------|------------------|-----------------|-------------------|
| EtablissementService | 9 | 6 | 3 |
| CycleService | 6 | 5 | 1 |
| NiveauService | 8 | 7 | 1 |
| AnneeScolaireService | 9 | 6 | 3 |
| EleveService | 10 | 6 | 4 |
| InscriptionService | 11 | 6 | 5 |
| FraisScolaireService | 9 | 7 | 2 |
| TranchePaiementService | 8 | 6 | 1 |
| FraisDiversService | 9 | 7 | 2 |
| FraisInscriptionService | 8 | 7 | 1 |
| PaiementService | 14 | 6 | 8 |
| DocumentService | 9 | 6 | 2 |
| BlocageService | 10 | 7 | 3 |
| NotificationService | 12 | 7 | 4 |
| RecuService | 11 | 6 | 5 |
| UtilisateurService | 10 | 7 | 3 |
| AccesEtablissementService | 7 | 5 | 2 |
| LogService | 11 | 5 | 6 |
| **TOTAL** | **169** | **105** | **64** |

---

## 🎭 Cas d'Utilisation par Acteur

### 🖥️ **Secrétaire** (Application Desktop/Web)

**Gestion de la structure de l'école**
- ✅ Établissement: `creerEtablissement`, `modifierEtablissement`, `supprimerEtablissement`
- ✅ Niveaux: `configurerNiveauxEtablissement`
- ✅ Frais: `definirFraisScolaire`, `definirFraisDivers`
- ✅ Année: `ouvrirAnneeScolaire`, `cloturerAnneeScolaire`

**Gestion des Élèves**
- ✅ Élève: `creerDossierEleve`, `modifierEleve`, `archiverEleve`

**Gestion des Inscriptions**
- ✅ Inscription: `enregistrerDossierInscription`, `validerPiecesInscription`, `enregistrerPaiementInscription`

**Gestion des Paiements Scolaires**
- ✅ Paiement: `enregistrerPaiement`, `associerPaiementAInscription`, `visualiserEchéancier`, `genererRecuPaiement`
- ✅ Reçu: `genererRecuInscription`, `traiterRecuDemande`

**Suivi & Reporting**
- ✅ Afficher les impayés
- ✅ Visualiser les élèves en retard

**Gestion des Blocages**
- ✅ Blocage: `bloquerInscription`, `leverBlocage`

---

### 📱 **Directeur** (Application Mobile)

**Tableau de bord**
- ✅ Consulter le montant total perçu
- ✅ Consulter le montant total des impayés
- ✅ Voir le nombre d'élèves en retard

**Consultation par Filtres**
- ✅ Établissement: `findAll`, filtrer par établissement
- ✅ Niveau: `findByNiveauId`
- ✅ Cycle: `findByCycleId`
- ✅ Mauvais payeurs: `obtenirPaiementsEnRetard`

**Consultation Individuelle**
- ✅ Élève: `rechercherEleve`
- ✅ Paiement: `visualiserEchéancier`, `calculerSoldeRestant`

**Gestion Documentaire**
- ✅ Reçu: `demanderRecuNumerise`, déclenche `notifierReçuDisponible`
- ✅ Notification: `marquerCommeLue`, `obtenirNotificationsNonLues`

---

### ⚙️ **Système** (Backend)

**Notifications**
- ✅ `notifierReçuDisponible` - Notifier le directeur
- ✅ `alerterImpayesCritiques` - Alerter en cas d'impayés

**Calculs**
- ✅ `calculerSoldeRestant` - **Calcul automatique du solde**

**Sécurité**
- ✅ `authentifier` - Authentification JWT
- ✅ `LogService` - Audit complet des actions

---

## 🚀 Opérations Critiques (Avec ⭐ Priorité)

1. ⭐ **PaiementService.calculerSoldeRestant** - Cœur du système
2. ⭐ **BlocageService.bloquerInscription** - Contrôle d'accès examen
3. ⭐ **NotificationService.notifierReçuDisponible** - Communication
4. ⭐ **InscriptionService.validerPiecesInscription** - Validation
5. ⭐ **RecuService.genererRecuPaiement** - Génération PDF
6. ⭐ **LogService** - Audit trail complet

---

## 📝 Notes

- **Total de 169 opérations** implementées dans 18 services
- Toutes les entités ont des opérations CRUD de base
- Les opérations métier couvrent les 18 cas d'usage définis
- Prêt pour la génération des contrôleurs REST (18 controllers)
- Base pour les tests unitaires et d'intégration

