# 👑 SUPER_ADMIN - Endpoints et Permissions

**Date:** 7 Mars 2026  
**Status:** ✅ Rôle SUPER_ADMIN ajouté

---

## 🎯 Définition du SUPER_ADMIN

Le **SUPER_ADMIN** est un rôle administrateur système qui a un accès complet à toutes les ressources et fonctionnalités du système. C'est le niveau d'accès le plus élevé.

**Permissions:**
- ✅ Accès COMPLET à TOUS les endpoints
- ✅ Gestion des utilisateurs (création, modification, suppression, activation/désactivation)
- ✅ Gestion des rôles et permissions
- ✅ Gestion des établissements (création, modification, suppression)
- ✅ Consultation et modification de TOUS les logs d'audit
- ✅ Gestion des configurations système
- ✅ Accès illimité à tous les rapports et données

---

## 📋 ENDPOINTS DU SUPER_ADMIN

### 🔐 1. GESTION DES UTILISATEURS (Exclusif SUPER_ADMIN)

| Endpoint | Méthode | Description | Permission |
|----------|---------|-------------|-----------|
| `/api/v1/utilisateurs` | POST | Créer un nouvel utilisateur | SUPER_ADMIN |
| `/api/v1/utilisateurs` | GET | Lister tous les utilisateurs | SUPER_ADMIN |
| `/api/v1/utilisateurs/{id}` | GET | Récupérer détails utilisateur | SUPER_ADMIN |
| `/api/v1/utilisateurs/{id}` | PUT | Modifier utilisateur | SUPER_ADMIN |
| `/api/v1/utilisateurs/{id}` | DELETE | Supprimer/Désactiver utilisateur | SUPER_ADMIN |
| `/api/v1/utilisateurs/role/{role}` | GET | Lister utilisateurs par rôle | SUPER_ADMIN |
| `/api/v1/utilisateurs/{id}/activer` | POST | Réactiver utilisateur | SUPER_ADMIN |
| `/api/v1/utilisateurs/{id}/desactiver` | POST | Désactiver utilisateur | SUPER_ADMIN |

### 🏢 2. GESTION DES ÉTABLISSEMENTS

| Endpoint | Méthode | Description | Permission |
|----------|---------|-------------|-----------|
| `/api/v1/etablissements` | POST | Créer établissement | SUPER_ADMIN |
| `/api/v1/etablissements` | GET | Lister établissements | SUPER_ADMIN |
| `/api/v1/etablissements/{id}` | GET | Récupérer établissement | SUPER_ADMIN |
| `/api/v1/etablissements/{id}` | PUT | Modifier établissement | SUPER_ADMIN |
| `/api/v1/etablissements/{id}` | DELETE | Supprimer établissement | SUPER_ADMIN |

### 🔗 3. GESTION DES ACCÈS AUX ÉTABLISSEMENTS

| Endpoint | Méthode | Description | Permission |
|----------|---------|-------------|-----------|
| `/api/v1/acces-etablissement` | POST | Accorder accès établissement | SUPER_ADMIN |
| `/api/v1/acces-etablissement` | GET | Lister tous les accès | SUPER_ADMIN |
| `/api/v1/acces-etablissement/{id}` | GET | Récupérer accès | SUPER_ADMIN |
| `/api/v1/acces-etablissement/utilisateur/{userId}` | GET | Établissements d'un utilisateur | SUPER_ADMIN |
| `/api/v1/acces-etablissement/etablissement/{etabId}` | GET | Utilisateurs d'un établissement | SUPER_ADMIN |
| `/api/v1/acces-etablissement/{id}` | DELETE | Révoquer accès | SUPER_ADMIN |

### 📊 4. GESTION DES LOGS (Exclusif SUPER_ADMIN)

| Endpoint | Méthode | Description | Permission |
|----------|---------|-------------|-----------|
| `/api/v1/logs` | GET | Lister tous les logs | SUPER_ADMIN |
| `/api/v1/logs/{id}` | GET | Récupérer un log | SUPER_ADMIN |
| `/api/v1/logs/utilisateur/{userId}` | GET | Logs d'un utilisateur | SUPER_ADMIN |
| `/api/v1/logs/entite/{entite}/{entiteId}` | GET | Logs d'une entité | SUPER_ADMIN |
| `/api/v1/logs/action/{action}` | GET | Logs par action | SUPER_ADMIN |
| `/api/v1/logs/{id}` | DELETE | Supprimer un log | SUPER_ADMIN |

### 🎓 5. GESTION DES CYCLES & NIVEAUX

| Endpoint | Méthode | Description | Permission |
|----------|---------|-------------|-----------|
| `/api/v1/cycles` | POST | Créer cycle | SUPER_ADMIN |
| `/api/v1/cycles` | GET | Lister cycles | SUPER_ADMIN |
| `/api/v1/cycles/{id}` | GET | Récupérer cycle | SUPER_ADMIN |
| `/api/v1/cycles/{id}` | PUT | Modifier cycle | SUPER_ADMIN |
| `/api/v1/cycles/{id}` | DELETE | Supprimer cycle | SUPER_ADMIN |
| `/api/v1/niveaux` | POST | Créer niveau | SUPER_ADMIN |
| `/api/v1/niveaux` | GET | Lister niveaux | SUPER_ADMIN |
| `/api/v1/niveaux/{id}` | GET | Récupérer niveau | SUPER_ADMIN |
| `/api/v1/niveaux/{id}` | PUT | Modifier niveau | SUPER_ADMIN |
| `/api/v1/niveaux/{id}` | DELETE | Supprimer niveau | SUPER_ADMIN |

### 💰 6. GESTION DES FRAIS

| Endpoint | Méthode | Description | Permission |
|----------|---------|-------------|-----------|
| `/api/v1/frais-scolaires` | POST | Créer frais scolaires | SUPER_ADMIN |
| `/api/v1/frais-scolaires` | GET | Lister frais | SUPER_ADMIN |
| `/api/v1/frais-scolaires/{id}` | GET | Récupérer frais | SUPER_ADMIN |
| `/api/v1/frais-scolaires/{id}` | PUT | Modifier frais | SUPER_ADMIN |
| `/api/v1/frais-scolaires/{id}` | DELETE | Supprimer frais | SUPER_ADMIN |
| `/api/v1/tranches-paiement` | POST | Créer tranche | SUPER_ADMIN |
| `/api/v1/tranches-paiement` | GET | Lister tranches | SUPER_ADMIN |
| `/api/v1/tranches-paiement/{id}` | GET | Récupérer tranche | SUPER_ADMIN |
| `/api/v1/tranches-paiement/{id}` | PUT | Modifier tranche | SUPER_ADMIN |
| `/api/v1/tranches-paiement/{id}` | DELETE | Supprimer tranche | SUPER_ADMIN |
| `/api/v1/frais-divers` | POST | Créer frais divers | SUPER_ADMIN |
| `/api/v1/frais-divers` | GET | Lister frais divers | SUPER_ADMIN |
| `/api/v1/frais-divers/{id}` | GET | Récupérer frais | SUPER_ADMIN |
| `/api/v1/frais-divers/{id}` | PUT | Modifier frais | SUPER_ADMIN |
| `/api/v1/frais-divers/{id}` | DELETE | Supprimer frais | SUPER_ADMIN |
| `/api/v1/frais-inscription` | POST | Créer frais inscription | SUPER_ADMIN |
| `/api/v1/frais-inscription` | GET | Lister frais inscription | SUPER_ADMIN |
| `/api/v1/frais-inscription/{id}` | GET | Récupérer frais | SUPER_ADMIN |
| `/api/v1/frais-inscription/{id}` | PUT | Modifier frais | SUPER_ADMIN |
| `/api/v1/frais-inscription/{id}` | DELETE | Supprimer frais | SUPER_ADMIN |

### 📅 7. GESTION DES ANNÉES SCOLAIRES

| Endpoint | Méthode | Description | Permission |
|----------|---------|-------------|-----------|
| `/api/v1/annees-scolaires` | POST | Créer année scolaire | SUPER_ADMIN |
| `/api/v1/annees-scolaires` | GET | Lister années | SUPER_ADMIN |
| `/api/v1/annees-scolaires/{id}` | GET | Récupérer année | SUPER_ADMIN |
| `/api/v1/annees-scolaires/{id}` | PUT | Modifier année | SUPER_ADMIN |
| `/api/v1/annees-scolaires/{id}` | DELETE | Supprimer année | SUPER_ADMIN |
| `/api/v1/annees-scolaires/active` | GET | Récupérer année active | SUPER_ADMIN |

### 👨‍🎓 8. GESTION DES ÉLÈVES

| Endpoint | Méthode | Description | Permission |
|----------|---------|-------------|-----------|
| `/api/v1/eleves` | POST | Créer élève | SUPER_ADMIN |
| `/api/v1/eleves` | GET | Lister élèves | SUPER_ADMIN |
| `/api/v1/eleves/{id}` | GET | Récupérer élève | SUPER_ADMIN |
| `/api/v1/eleves/{id}` | PUT | Modifier élève | SUPER_ADMIN |
| `/api/v1/eleves/{id}/archiver` | POST | Archiver élève | SUPER_ADMIN |

### 📝 9. GESTION DES INSCRIPTIONS

| Endpoint | Méthode | Description | Permission |
|----------|---------|-------------|-----------|
| `/api/v1/inscriptions` | POST | Créer inscription | SUPER_ADMIN |
| `/api/v1/inscriptions` | GET | Lister inscriptions | SUPER_ADMIN |
| `/api/v1/inscriptions/{id}` | GET | Récupérer inscription | SUPER_ADMIN |
| `/api/v1/inscriptions/{id}/valider-pieces` | PUT | Valider pièces | SUPER_ADMIN |
| `/api/v1/inscriptions/{id}/paiement` | POST | Enregistrer paiement | SUPER_ADMIN |
| `/api/v1/inscriptions/{id}/generer-recu` | POST | Générer reçu | SUPER_ADMIN |

### 💳 10. GESTION DES PAIEMENTS

| Endpoint | Méthode | Description | Permission |
|----------|---------|-------------|-----------|
| `/api/v1/paiements` | POST | Créer paiement | SUPER_ADMIN |
| `/api/v1/paiements` | GET | Lister paiements | SUPER_ADMIN |
| `/api/v1/paiements/{id}` | GET | Récupérer paiement | SUPER_ADMIN |
| `/api/v1/paiements/{id}` | PUT | Modifier paiement | SUPER_ADMIN |
| `/api/v1/paiements/{id}` | DELETE | Supprimer paiement | SUPER_ADMIN |
| `/api/v1/paiements/inscription/{inscriptionId}` | GET | Historique paiements | SUPER_ADMIN |
| `/api/v1/paiements/inscription/{inscriptionId}/echeancier` | GET | Échéancier | SUPER_ADMIN |
| `/api/v1/paiements/inscription/{inscriptionId}/solde-restant` | GET | Solde restant | SUPER_ADMIN |
| `/api/v1/paiements/inscription/{inscriptionId}/a-jour` | GET | Vérifier à jour | SUPER_ADMIN |
| `/api/v1/paiements/classe/{niveauId}/impayes` | GET | Impayés par classe | SUPER_ADMIN |
| `/api/v1/paiements/eleves-en-retard` | GET | Élèves en retard | SUPER_ADMIN |
| `/api/v1/paiements/eleves/statut/{statut}` | GET | Filtrer par statut | SUPER_ADMIN |
| `/api/v1/paiements/{id}/generer-recu` | POST | Générer reçu | SUPER_ADMIN |
| `/api/v1/paiements/{id}/telecharger-recu` | POST | Télécharger reçu | SUPER_ADMIN |

### 🚫 11. GESTION DES BLOCAGES

| Endpoint | Méthode | Description | Permission |
|----------|---------|-------------|-----------|
| `/api/v1/blocages` | POST | Créer blocage | SUPER_ADMIN |
| `/api/v1/blocages` | GET | Lister blocages | SUPER_ADMIN |
| `/api/v1/blocages/{id}` | GET | Récupérer blocage | SUPER_ADMIN |
| `/api/v1/blocages/{id}/lever` | POST | Lever blocage | SUPER_ADMIN |
| `/api/v1/blocages/{id}` | DELETE | Supprimer blocage | SUPER_ADMIN |

### 📄 12. GESTION DES DOCUMENTS

| Endpoint | Méthode | Description | Permission |
|----------|---------|-------------|-----------|
| `/api/v1/documents` | POST | Créer document | SUPER_ADMIN |
| `/api/v1/documents` | GET | Lister documents | SUPER_ADMIN |
| `/api/v1/documents/{id}` | GET | Récupérer document | SUPER_ADMIN |
| `/api/v1/documents/{id}` | PUT | Modifier document | SUPER_ADMIN |
| `/api/v1/documents/{id}` | DELETE | Supprimer document | SUPER_ADMIN |
| `/api/v1/documents/{id}/telecharger` | GET | Télécharger document | SUPER_ADMIN |

### 🔔 13. GESTION DES NOTIFICATIONS

| Endpoint | Méthode | Description | Permission |
|----------|---------|-------------|-----------|
| `/api/v1/notifications` | POST | Créer notification | SUPER_ADMIN |
| `/api/v1/notifications` | GET | Lister notifications | SUPER_ADMIN |
| `/api/v1/notifications/{id}` | GET | Récupérer notification | SUPER_ADMIN |
| `/api/v1/notifications/non-lues` | GET | Notifications non lues | SUPER_ADMIN |
| `/api/v1/notifications/utilisateur/{userId}/non-lues` | GET | Non lues utilisateur | SUPER_ADMIN |
| `/api/v1/notifications/{id}/marquer-lu` | POST | Marquer comme lu | SUPER_ADMIN |
| `/api/v1/notifications/{id}` | DELETE | Supprimer notification | SUPER_ADMIN |

### 📋 14. GESTION DES REÇUS

| Endpoint | Méthode | Description | Permission |
|----------|---------|-------------|-----------|
| `/api/v1/recus/inscription` | POST | Générer reçu inscription | SUPER_ADMIN |
| `/api/v1/recus/paiement` | POST | Générer reçu paiement | SUPER_ADMIN |
| `/api/v1/recus` | GET | Lister reçus | SUPER_ADMIN |
| `/api/v1/recus/{id}` | GET | Récupérer reçu | SUPER_ADMIN |
| `/api/v1/recus/{id}` | PUT | Modifier reçu | SUPER_ADMIN |
| `/api/v1/recus/{id}` | DELETE | Supprimer reçu | SUPER_ADMIN |
| `/api/v1/recus/{id}/telecharger` | POST | Télécharger reçu | SUPER_ADMIN |
| `/api/v1/recus/{id}/upload-signe` | POST | Upload reçu signé | SUPER_ADMIN |

---

## 📊 RÉSUMÉ DES ENDPOINTS

| Catégorie | Nombre d'Endpoints |
|-----------|-------------------|
| Utilisateurs | 8 |
| Établissements | 5 |
| Accès | 6 |
| Logs | 6 |
| Cycles & Niveaux | 10 |
| Frais | 21 |
| Années Scolaires | 6 |
| Élèves | 5 |
| Inscriptions | 6 |
| Paiements | 14 |
| Blocages | 5 |
| Documents | 6 |
| Notifications | 7 |
| Reçus | 8 |
| **TOTAL** | **113** |

---

## 🔐 EXEMPLE D'UTILISATION AVEC JWT

**Header Authorization:**
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

**Payload JWT:**
```json
{
  "sub": "550e8400-e29b-41d4-a716-446655440000",
  "role": "SUPER_ADMIN",
  "email": "admin@sophia.local",
  "etablissementIds": [1, 2, 3, ...],
  "exp": 1741500000
}
```

---

## ✅ PERMISSIONS PAR RÔLE

| Fonctionnalité | SUPER_ADMIN | DIRECTEUR | SECRÉTAIRE |
|---------------|------------|-----------|-----------|
| Gestion Utilisateurs | ✅ | ❌ | ❌ |
| Gestion Établissements | ✅ | ❌ | ✅ |
| Gestion Accès | ✅ | ❌ | ❌ |
| Consultation Logs | ✅ | ❌ | ❌ |
| Gestion Cycles/Niveaux | ✅ | ❌ | ✅ |
| Gestion Frais | ✅ | ❌ | ✅ |
| Gestion Années | ✅ | ❌ | ✅ |
| Gestion Élèves | ✅ | ✅ | ✅ |
| Gestion Inscriptions | ✅ | ✅ | ✅ |
| Gestion Paiements | ✅ | ✅ | ✅ |
| Gestion Blocages | ✅ | ✅ | ✅ |
| Gestion Documents | ✅ | ✅ | ✅ |
| Notifications | ✅ | ✅ | ✅ |
| Reçus | ✅ | ✅ | ✅ |

---

**Status: ✅ SUPER_ADMIN ROLE ADDED & DOCUMENTED**

