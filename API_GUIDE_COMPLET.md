Chap# 🚀 Guide Complet - Fonctionnement de l'API Sophia

**Date: 7 Mars 2026**  
**Version: 1.0.0**

---

## 📚 Table des Matières

1. [Architecture Globale](#architecture-globale)
2. [Flux de Données](#flux-de-données)
3. [Ordre de Création des Éléments](#ordre-de-création-des-éléments)
4. [Workflows Principaux](#workflows-principaux)
5. [Exemples Pratiques](#exemples-pratiques)
6. [Endpoints par Cas d'Usage](#endpoints-par-cas-dusage)

---

## 🏗️ Architecture Globale

### Couches de l'Application

```
┌─────────────────────────────────────────┐
│  INTERFACES (REST Controllers)          │
│  @RestController + @RequestMapping      │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│  APPLICATION (Services + DTOs)          │
│  Logique métier + Transfert de données  │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│  DOMAIN (Models + Repositories)         │
│  Entités + Interfaces de persistance    │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│  INFRASTRUCTURE (JPA + Mappers)         │
│  Implémentation DB + Conversion         │
└─────────────────────────────────────────┘
```

### 18 Contrôleurs REST (130+ endpoints)

```
/api/v1/
├── /etablissements           → Gestion des écoles
├── /cycles                   → Préscolaire, Primaire, Collège, Lycée
├── /niveaux                  → 6ème, 5ème, CM2, etc.
├── /annees-scolaires         → 2024-2025, 2025-2026
├── /eleves                   → Dossiers élèves
├── /inscriptions             → Inscription à un niveau
├── /frais-scolaires          → Montant total par niveau/année
├── /tranches-paiement        → 1ère, 2ème, 3ème tranche
├── /frais-divers             → Frais d'examen, etc.
├── /frais-inscription        → Frais d'inscription par cycle
├── /paiements                → Enregistrement paiements ⭐
├── /documents                → Bulletins, attestations
├── /blocages                 → Bloquer examen/évaluation
├── /notifications            → Alertes système
├── /recus                    → PDF de paiement
├── /utilisateurs             → Secrétaires & Directeurs
├── /acces-etablissements     → Droits d'accès
└── /logs                     → Audit trail
```

---

## 🔄 Flux de Données

### Requête HTTP → Réponse JSON

```
1. CLIENT (cURL, Postman, Frontend)
           ↓
2. CONTROLLER (Récoit JSON, mappe vers DTO)
           ↓
3. SERVICE (Exécute logique métier)
           ↓
4. REPOSITORY (Accède à la DB)
           ↓
5. DATABASE (PostgreSQL)
           ↓
6. SERVICE (Récupère données)
           ↓
7. MAPPER (Convertit Entity → DTO)
           ↓
8. CONTROLLER (Retourne JSON)
           ↓
9. CLIENT (Reçoit réponse)
```

### Exemple Concret

```json
POST /api/v1/paiements
{
  "inscriptionId": 1,
  "typePaiement": "SCOLARITE",
  "montant": 50000,
  "modePaiement": "ESPECES",
  "utilisateurId": "uuid-secretaire"
}
        ↓
PaiementController.create()
        ↓
PaiementService.enregistrerPaiement()
        ↓
PaiementRepository.save()
        ↓
INSERT INTO paiement (...)
        ↓
HTTP 201 Created
{
  "id": 42,
  "inscriptionId": 1,
  "montant": 50000,
  "datePaiement": "2026-03-07T10:30:00Z"
}
```

---

## 📋 Ordre de Création des Éléments

### Phase 1️⃣: Configuration de Base (Obligatoire en premier)

#### 1. Créer les Utilisateurs
```bash
POST /api/v1/utilisateurs
{
  "nom": "Marie Dupont",
  "email": "marie.dupont@sophia.fr",
  "motDePasse": "hashed_password",
  "role": "SECRETAIRE",
  "telephone": "0123456789",
  "estActif": true
}
```

**Réponse:**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "nom": "Marie Dupont",
  "role": "SECRETAIRE",
  "createdAt": "2026-03-07T10:00:00Z"
}
```

#### 2. Créer les Établissements
```bash
POST /api/v1/etablissements
{
  "nom": "Institut Sophia",
  "adresse": "123 Rue de la Paix, Dakar",
  "telephone": "+221701234567",
  "email": "contact@sophia-institut.sn",
  "statut": "ACTIF"
}
```

**Réponse:**
```json
{
  "id": 1,
  "nom": "Institut Sophia",
  "statut": "ACTIF",
  "createdAt": "2026-03-07T10:05:00Z"
}
```

#### 3. Accorder l'Accès aux Utilisateurs
```bash
POST /api/v1/acces-etablissements
{
  "utilisateurId": "550e8400-e29b-41d4-a716-446655440000",
  "etablissementId": 1
}
```

---

### Phase 2️⃣: Structure Scolaire (Configuration pédagogique)

#### 4. Créer les Cycles
```bash
POST /api/v1/cycles
{
  "nom": "Collège",
  "description": "Classes de 6ème à 3ème",
  "ordre": 3
}

POST /api/v1/cycles
{
  "nom": "Primaire",
  "ordre": 2
}

POST /api/v1/cycles
{
  "nom": "Lycée",
  "ordre": 4
}
```

**Réponse pour chaque:**
```json
{
  "id": 1,
  "nom": "Collège",
  "ordre": 3,
  "createdAt": "2026-03-07T10:10:00Z"
}
```

#### 5. Créer les Niveaux (pour chaque cycle)
```bash
# Pour Collège (id: 1)
POST /api/v1/niveaux
{
  "nom": "6ème",
  "cycleId": 1,
  "etablissementId": 1,
  "ordre": 1
}

POST /api/v1/niveaux
{
  "nom": "5ème",
  "cycleId": 1,
  "etablissementId": 1,
  "ordre": 2
}

POST /api/v1/niveaux
{
  "nom": "4ème",
  "cycleId": 1,
  "etablissementId": 1,
  "ordre": 3
}

POST /api/v1/niveaux
{
  "nom": "3ème",
  "cycleId": 1,
  "etablissementId": 1,
  "ordre": 4
}
```

---

### Phase 3️⃣: Configuration Financière (Frais & Échéancier)

#### 6. Créer l'Année Scolaire Active
```bash
POST /api/v1/annees-scolaires
{
  "libelle": "2024-2025",
  "dateDebut": "2024-09-01",
  "dateFin": "2025-06-30",
  "estActive": true
}
```

**Réponse:**
```json
{
  "id": 1,
  "libelle": "2024-2025",
  "estActive": true,
  "createdAt": "2026-03-07T10:15:00Z"
}
```

#### 7. Définir les Frais d'Inscription (par cycle)
```bash
POST /api/v1/frais-inscription
{
  "cycleId": 1,
  "anneeScolaireId": 1,
  "montant": 50000
}
```

#### 8. Définir les Frais Scolaires (par niveau/année)
```bash
POST /api/v1/frais-scolaires
{
  "niveauId": 1,
  "anneeScolaireId": 1,
  "montantTotal": 600000,
  "description": "Frais 6ème 2024-2025"
}
```

**Réponse:**
```json
{
  "id": 1,
  "niveauId": 1,
  "montantTotal": 600000,
  "createdAt": "2026-03-07T10:20:00Z"
}
```

#### 9. Créer les Tranches de Paiement
```bash
POST /api/v1/tranches-paiement
{
  "fraisScolaireId": 1,
  "nomTranche": "1ère tranche",
  "montant": 200000,
  "dateLimiteDebut": "2024-09-01",
  "dateL imiteFin": "2024-10-31",
  "ordre": 1
}

POST /api/v1/tranches-paiement
{
  "fraisScolaireId": 1,
  "nomTranche": "2ème tranche",
  "montant": 200000,
  "dateLimiteDebut": "2024-11-01",
  "dateLimiteFin": "2024-12-31",
  "ordre": 2
}

POST /api/v1/tranches-paiement
{
  "fraisScolaireId": 1,
  "nomTranche": "3ème tranche",
  "montant": 200000,
  "dateLimiteDebut": "2025-01-01",
  "dateLimiteFin": "2025-03-31",
  "ordre": 3
}
```

#### 10. Ajouter les Frais Divers (le cas échéant)
```bash
POST /api/v1/frais-divers
{
  "niveauId": 4,
  "libelle": "Frais d'examen 3ème",
  "montant": 30000,
  "anneeScolaireId": 1
}
```

---

### Phase 4️⃣: Gestion des Élèves

#### 11. Créer un Dossier Élève
```bash
POST /api/v1/eleves
{
  "nom": "Sow",
  "prenom": "Amadou",
  "dateNaissance": "2010-03-15",
  "lieuNaissance": "Dakar",
  "sexe": "M",
  "nationalite": "Sénégalaise",
  "adresse": "Rue 1, Dakar",
  "nomTuteur": "Mamadou Sow",
  "telephoneTuteur": "+221701234567",
  "emailTuteur": "mamadou@email.com",
  "photoPath": "/photos/amadou.jpg",
  "acteNaissancePath": "/documents/acte_naissance.pdf",
  "etablissementId": 1,
  "statutDossier": "INCOMPLET"
}
```

**Réponse:**
```json
{
  "id": "660e8400-e29b-41d4-a716-446655440001",
  "matricule": "EL-2026-00001",
  "nom": "Sow",
  "prenom": "Amadou",
  "statutDossier": "INCOMPLET",
  "createdAt": "2026-03-07T10:30:00Z"
}
```

---

### Phase 5️⃣: Inscriptions & Paiements

#### 12. Créer une Inscription
```bash
POST /api/v1/inscriptions
{
  "eleveId": "660e8400-e29b-41d4-a716-446655440001",
  "niveauId": 1,
  "anneeScolaireId": 1,
  "dateInscription": "2024-08-15",
  "statut": "ACTIVE",
  "commentaire": "Inscription en 6ème"
}
```

**Réponse:**
```json
{
  "id": 1,
  "eleveId": "660e8400-e29b-41d4-a716-446655440001",
  "niveauId": 1,
  "statut": "ACTIVE",
  "createdAt": "2026-03-07T10:35:00Z"
}
```

#### 13. Enregistrer le Paiement des Frais d'Inscription
```bash
POST /api/v1/paiements
{
  "inscriptionId": 1,
  "typePaiement": "INSCRIPTION",
  "montant": 50000,
  "datePaiement": "2024-08-20T10:00:00Z",
  "modePaiement": "ESPECES",
  "referenceId": 1,
  "utilisateurId": "550e8400-e29b-41d4-a716-446655440000"
}
```

**Réponse:**
```json
{
  "id": 1,
  "inscriptionId": 1,
  "typePaiement": "INSCRIPTION",
  "montant": 50000,
  "modePaiement": "ESPECES",
  "datePaiement": "2024-08-20T10:00:00Z",
  "createdAt": "2026-03-07T10:36:00Z"
}
```

#### 14. Générer un Reçu
```bash
POST /api/v1/recus/inscription
?paiementId=1
&secretaireId=550e8400-e29b-41d4-a716-446655440000
```

**Réponse:**
```json
{
  "id": 1,
  "paiementId": 1,
  "cheminFichier": "/recus/recu_001.pdf",
  "statut": "DISPONIBLE",
  "createdAt": "2026-03-07T10:37:00Z"
}
```

#### 15. Enregistrer les Paiements de Scolarité (1ère, 2ème, 3ème tranche)
```bash
# 1ère tranche
POST /api/v1/paiements
{
  "inscriptionId": 1,
  "typePaiement": "SCOLARITE",
  "referenceId": 1,
  "montant": 200000,
  "modePaiement": "CHEQUE",
  "datePaiement": "2024-09-20T10:00:00Z",
  "utilisateurId": "550e8400-e29b-41d4-a716-446655440000"
}

# 2ème tranche
POST /api/v1/paiements
{
  "inscriptionId": 1,
  "typePaiement": "SCOLARITE",
  "referenceId": 2,
  "montant": 200000,
  "modePaiement": "CHEQUE",
  "datePaiement": "2024-12-20T10:00:00Z",
  "utilisateurId": "550e8400-e29b-41d4-a716-446655440000"
}

# 3ème tranche
POST /api/v1/paiements
{
  "inscriptionId": 1,
  "typePaiement": "SCOLARITE",
  "referenceId": 3,
  "montant": 200000,
  "modePaiement": "CHEQUE",
  "datePaiement": "2025-02-20T10:00:00Z",
  "utilisateurId": "550e8400-e29b-41d4-a716-446655440000"
}
```

---

## 🎯 Workflows Principaux

### Workflow 1: Inscription Complète d'un Élève

```
1. CREER UTILISATEUR (Secrétaire)
   ↓
2. CREER ETABLISSEMENT
   ↓
3. ACCORDER ACCES (Utilisateur → Établissement)
   ↓
4. CREER CYCLES & NIVEAUX
   ↓
5. DEFINIR FRAIS & TRANCHES
   ↓
6. CREER DOSSIER ELEVE
   ↓
7. CREER INSCRIPTION
   ↓
8. ENREGISTRER PAIEMENT INSCRIPTION
   ↓
9. GENERER RECU INSCRIPTION
   ↓
✅ INSCRIPTION COMPLETE
```

### Workflow 2: Suivi Paiement Annuel

```
INSCRIPTION CREEE
   ↓
PAIEMENT 1ère TRANCHE
   ↓
GENERER RECU TRANCHE 1
   ↓
PAIEMENT 2ème TRANCHE
   ↓
GENERER RECU TRANCHE 2
   ↓
PAIEMENT 3ème TRANCHE
   ↓
GENERER RECU TRANCHE 3
   ↓
✅ FRAIS SCOLAIRES PAYES
```

### Workflow 3: Gestion des Retards

```
PAIEMENT EN RETARD DETECTE
   ↓
CREER NOTIFICATION
   ↓
NOTIFIER DIRECTEUR
   ↓
OPTIONNEL: BLOQUER INSCRIPTION
   ↓
PAIEMENT EFFECTUE
   ↓
LEVER BLOCAGE
   ↓
✅ SITUATION REGULER

```

---

## 📝 Exemples Pratiques

### Scénario Complet: Inscription d'Amadou Sow

#### Étape 1: Préparation
```bash
# 1. Créer l'utilisateur secrétaire
curl -X POST http://localhost:8080/api/v1/utilisateurs \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Marie Dupont",
    "email": "marie@sophia.fr",
    "motDePasse": "hashed_pwd",
    "role": "SECRETAIRE"
  }'
# → userid: "uuid-marie"

# 2. Créer l'établissement
curl -X POST http://localhost:8080/api/v1/etablissements \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Institut Sophia",
    "adresse": "123 Rue X",
    "telephone": "+221701234567",
    "email": "contact@sophia.sn",
    "statut": "ACTIF"
  }'
# → id: 1

# 3. Accorder accès
curl -X POST http://localhost:8080/api/v1/acces-etablissements \
  -H "Content-Type: application/json" \
  -d '{
    "utilisateurId": "uuid-marie",
    "etablissementId": 1
  }'
```

#### Étape 2: Configuration Structure
```bash
# 4. Créer Cycle Collège
curl -X POST http://localhost:8080/api/v1/cycles \
  -H "Content-Type: application/json" \
  -d '{"nom": "Collège", "ordre": 3}'
# → id: 1

# 5. Créer Niveaux
curl -X POST http://localhost:8080/api/v1/niveaux \
  -H "Content-Type: application/json" \
  -d '{"nom": "6ème", "cycleId": 1, "etablissementId": 1, "ordre": 1}'
# → id: 1
```

#### Étape 3: Configuration Financière
```bash
# 6. Créer Année Scolaire
curl -X POST http://localhost:8080/api/v1/annees-scolaires \
  -H "Content-Type: application/json" \
  -d '{
    "libelle": "2024-2025",
    "dateDebut": "2024-09-01",
    "dateFin": "2025-06-30",
    "estActive": true
  }'
# → id: 1

# 7. Définir Frais Scolaires
curl -X POST http://localhost:8080/api/v1/frais-scolaires \
  -H "Content-Type: application/json" \
  -d '{
    "niveauId": 1,
    "anneeScolaireId": 1,
    "montantTotal": 600000
  }'
# → id: 1

# 8. Créer Tranches (1ère, 2ème, 3ème)
curl -X POST http://localhost:8080/api/v1/tranches-paiement \
  -H "Content-Type: application/json" \
  -d '{
    "fraisScolaireId": 1,
    "nomTranche": "1ère tranche",
    "montant": 200000,
    "dateLimiteDebut": "2024-09-01",
    "dateLimiteFin": "2024-10-31",
    "ordre": 1
  }'
# → id: 1, 2, 3
```

#### Étape 4: Inscription Élève
```bash
# 9. Créer dossier élève
curl -X POST http://localhost:8080/api/v1/eleves \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Sow",
    "prenom": "Amadou",
    "dateNaissance": "2010-03-15",
    "lieuNaissance": "Dakar",
    "sexe": "M",
    "etablissementId": 1,
    "statutDossier": "INCOMPLET"
  }'
# → eleveId: "uuid-amadou"

# 10. Créer inscription
curl -X POST http://localhost:8080/api/v1/inscriptions \
  -H "Content-Type: application/json" \
  -d '{
    "eleveId": "uuid-amadou",
    "niveauId": 1,
    "anneeScolaireId": 1,
    "statut": "ACTIVE"
  }'
# → inscriptionId: 1
```

#### Étape 5: Paiements
```bash
# 11. Paiement inscription
curl -X POST http://localhost:8080/api/v1/paiements \
  -H "Content-Type: application/json" \
  -d '{
    "inscriptionId": 1,
    "typePaiement": "INSCRIPTION",
    "montant": 50000,
    "modePaiement": "ESPECES",
    "utilisateurId": "uuid-marie"
  }'
# → paiementId: 1

# 12. Générer reçu
curl -X POST "http://localhost:8080/api/v1/recus/inscription?paiementId=1&secretaireId=uuid-marie"

# 13. Paiement 1ère tranche
curl -X POST http://localhost:8080/api/v1/paiements \
  -H "Content-Type: application/json" \
  -d '{
    "inscriptionId": 1,
    "typePaiement": "SCOLARITE",
    "referenceId": 1,
    "montant": 200000,
    "modePaiement": "CHEQUE",
    "utilisateurId": "uuid-marie"
  }'
```

---

## 📊 Endpoints par Cas d'Usage

### Cas 1: Directeur Consulte la Situation Financière

```
GET /api/v1/paiements
GET /api/v1/paiements/inscription/{inscriptionId}/solde-restant?montantTotal=600000
GET /api/v1/paiements/inscription/{inscriptionId}/a-jour?montantTotal=600000
GET /api/v1/paiements/inscription/{inscriptionId}/echeancier
GET /api/v1/notifications/destinataire/{directeurId}
```

### Cas 2: Secrétaire Enregistre un Paiement

```
POST /api/v1/paiements
GET /api/v1/paiements/{id}
POST /api/v1/recus/paiement?paiementId={id}&secretaireId={userId}&cheminFichier=/path
GET /api/v1/logs/utilisateur/{secretaireId}
```

### Cas 3: Bloquer un Élève pour Impayés

```
POST /api/v1/blocages/bloquer?inscriptionId={id}&typeBlocage=EXAMEN&raison="Impayés"
GET /api/v1/blocages/actifs
POST /api/v1/notifications (Notifier directeur)
POST /api/v1/paiements (Paiement)
POST /api/v1/blocages/{id}/lever
```

---

## 🔑 Points Clés

### ✅ Important à Retenir

1. **Ordre Strict de Création:**
   - Utilisateurs → Établissements → Accès
   - Cycles → Niveaux → Année Scolaire
   - Frais → Tranches
   - Élèves → Inscriptions → Paiements

2. **Dépendances:**
   - Un Niveau dépend d'un Cycle
   - Un Cycle dépend d'un Établissement
   - Une Inscription dépend d'un Élève, Niveau, Année Scolaire
   - Un Paiement dépend d'une Inscription

3. **IDs à Conserver:**
   - ID Utilisateur (UUID) pour logs
   - ID Établissement pour tout
   - ID Cycle, Niveau, Année pour structure
   - ID Inscription pour paiements

4. **Logs Automatiques:**
   - Chaque création/modification/suppression logged
   - Accessible via `/api/v1/logs`
   - Traçabilité complète

---

## 📞 Aide & Support

### Codes HTTP Retournés

| Code | Signification |
|------|---------------|
| 200 | OK - Requête réussie |
| 201 | Created - Ressource créée |
| 204 | No Content - Suppression réussie |
| 400 | Bad Request - Données invalides |
| 404 | Not Found - Ressource inexistante |
| 500 | Internal Server Error |

### Démarrer l'API

```bash
mvn spring-boot:run
```

### Accéder à Swagger

```
http://localhost:8080/swagger-ui.html
```

---

**Fin du guide - Prêt à utiliser l'API! 🚀**

