# 🔐 AUTHENTIFICATION JWT & CONTRÔLE D'ACCÈS PAR RÔLE

**Date:** 7 Mars 2026  
**Status:** ✅ JWT Implémenté avec contraintes de rôle

---

## 🎯 Architecture de Sécurité

### Composants

1. **JwtService** - Génération et validation des tokens JWT
2. **JwtAuthenticationFilter** - Filtre pour extraire et valider les tokens
3. **SecurityConfig** - Configuration Spring Security avec règles d'accès
4. **AuthController** - Endpoints d'authentification et validation

---

## 📋 ENDPOINTS D'AUTHENTIFICATION

### 1. Login - Générer un Token JWT

```http
POST /api/v1/auth/login
Content-Type: application/json

{
  "userId": "550e8400-e29b-41d4-a716-446655440000",
  "email": "secretaire@sophia.local",
  "role": "SECRETAIRE"
}
```

**Réponse (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9...",
  "expiresIn": 86400,
  "userId": "550e8400-e29b-41d4-a716-446655440000",
  "email": "secretaire@sophia.local",
  "role": "SECRETAIRE"
}
```

### 2. Validate - Valider un Token

```http
GET /api/v1/auth/validate
Authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9...
```

**Réponse (200 OK):**
```json
{
  "valid": true,
  "userId": "550e8400-e29b-41d4-a716-446655440000",
  "email": "secretaire@sophia.local",
  "role": "SECRETAIRE"
}
```

### 3. Logout - Déconnecter

```http
POST /api/v1/auth/logout
Authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9...
```

**Réponse (200 OK):**
```json
{
  "message": "Déconnecté avec succès"
}
```

---

## 🔑 STRUCTURE DU TOKEN JWT

**Header:**
```json
{
  "alg": "HS512",
  "typ": "JWT"
}
```

**Payload:**
```json
{
  "sub": "550e8400-e29b-41d4-a716-446655440000",
  "email": "secretaire@sophia.local",
  "role": "SECRETAIRE",
  "iat": 1741500000,
  "exp": 1741586400
}
```

**Valeurs:**
- `sub`: UUID de l'utilisateur
- `email`: Email de l'utilisateur
- `role`: Rôle (SECRETAIRE, DIRECTEUR, SUPER_ADMIN)
- `iat`: Temps d'émission (Unix timestamp)
- `exp`: Temps d'expiration (86400 secondes = 24h)

---

## 📊 MATRICE D'ACCÈS PAR RÔLE

### 👑 SUPER_ADMIN
**Permissions:** Accès complet à TOUS les endpoints

| Catégorie | Accès |
|-----------|-------|
| Utilisateurs | ✅ COMPLET |
| Établissements | ✅ COMPLET |
| Accès établissements | ✅ COMPLET |
| Logs d'audit | ✅ COMPLET |
| Cycles & Niveaux | ✅ COMPLET |
| Frais (tous) | ✅ COMPLET |
| Années scolaires | ✅ COMPLET |
| Élèves | ✅ COMPLET |
| Inscriptions | ✅ COMPLET |
| Paiements | ✅ COMPLET |
| Blocages | ✅ COMPLET |
| Documents | ✅ COMPLET |
| Notifications | ✅ COMPLET |
| Reçus | ✅ COMPLET |

---

### 📋 SECRETAIRE
**Permissions:** Gestion complète de l'école et des élèves

| Catégorie | Accès |
|-----------|-------|
| Utilisateurs | ❌ NON |
| Établissements | ✅ COMPLET |
| Accès établissements | ❌ NON |
| Logs d'audit | ❌ NON |
| Cycles & Niveaux | ✅ COMPLET |
| Frais (tous) | ✅ COMPLET |
| Années scolaires | ✅ COMPLET |
| Élèves | ✅ COMPLET |
| Inscriptions | ✅ COMPLET |
| Paiements | ✅ COMPLET |
| Blocages | ✅ COMPLET |
| Documents | ✅ COMPLET |
| Notifications | ✅ COMPLET |
| Reçus | ✅ COMPLET |

---

### 📱 DIRECTEUR
**Permissions:** Consultation et reporting

| Catégorie | Accès |
|-----------|-------|
| Utilisateurs | ❌ NON |
| Établissements | ❌ NON |
| Accès établissements | ❌ NON |
| Logs d'audit | ❌ NON |
| Cycles & Niveaux | ❌ NON |
| Frais (tous) | ❌ NON |
| Années scolaires | ❌ NON |
| Élèves | ✅ LECTURE/CONSULTATION |
| Inscriptions | ✅ LECTURE/CONSULTATION |
| Paiements | ✅ LECTURE/CONSULTATION |
| Blocages | ✅ LECTURE/CONSULTATION |
| Documents | ✅ LECTURE/CONSULTATION |
| Notifications | ✅ LECTURE/CONSULTATION |
| Reçus | ✅ LECTURE/CONSULTATION |

---

## 🛡️ RÈGLES D'ACCÈS DÉTAILLÉES

### Endpoints Publics (PERMITALL)
```
POST /api/v1/auth/login          - Login (génération du token)
GET  /api/v1/auth/validate       - Validation du token
POST /api/v1/auth/logout         - Logout
GET  /swagger-ui/**              - Documentation Swagger
GET  /v3/api-docs/**             - Spécification OpenAPI
GET  /health                     - Health check
GET  /actuator/**                - Actuator endpoints
```

### SUPER_ADMIN Only
```
POST   /api/v1/utilisateurs/**   - Tous les endpoints utilisateurs
GET    /api/v1/utilisateurs/**   - Tous les endpoints utilisateurs
PUT    /api/v1/utilisateurs/**   - Tous les endpoints utilisateurs
DELETE /api/v1/utilisateurs/**   - Tous les endpoints utilisateurs

POST   /api/v1/acces-etablissement/**   - Tous les endpoints d'accès
GET    /api/v1/acces-etablissement/**   - Tous les endpoints d'accès
DELETE /api/v1/acces-etablissement/**   - Tous les endpoints d'accès

GET    /api/v1/logs/**           - Tous les endpoints de logs
DELETE /api/v1/logs/**           - Suppression des logs
```

### SECRETAIRE & SUPER_ADMIN
```
POST   /api/v1/etablissements/**        - CRUD établissements
GET    /api/v1/etablissements/**        - CRUD établissements
PUT    /api/v1/etablissements/**        - CRUD établissements
DELETE /api/v1/etablissements/**        - CRUD établissements

POST   /api/v1/cycles/**                - CRUD cycles
GET    /api/v1/cycles/**                - CRUD cycles
PUT    /api/v1/cycles/**                - CRUD cycles
DELETE /api/v1/cycles/**                - CRUD cycles

POST   /api/v1/niveaux/**               - CRUD niveaux
GET    /api/v1/niveaux/**               - CRUD niveaux
PUT    /api/v1/niveaux/**               - CRUD niveaux
DELETE /api/v1/niveaux/**               - CRUD niveaux

POST   /api/v1/frais-scolaires/**       - CRUD frais
GET    /api/v1/frais-scolaires/**       - CRUD frais
PUT    /api/v1/frais-scolaires/**       - CRUD frais
DELETE /api/v1/frais-scolaires/**       - CRUD frais

POST   /api/v1/tranches-paiement/**     - CRUD tranches
GET    /api/v1/tranches-paiement/**     - CRUD tranches
PUT    /api/v1/tranches-paiement/**     - CRUD tranches
DELETE /api/v1/tranches-paiement/**     - CRUD tranches

POST   /api/v1/frais-divers/**          - CRUD frais divers
GET    /api/v1/frais-divers/**          - CRUD frais divers
PUT    /api/v1/frais-divers/**          - CRUD frais divers
DELETE /api/v1/frais-divers/**          - CRUD frais divers

POST   /api/v1/frais-inscription/**     - CRUD frais inscription
GET    /api/v1/frais-inscription/**     - CRUD frais inscription
PUT    /api/v1/frais-inscription/**     - CRUD frais inscription
DELETE /api/v1/frais-inscription/**     - CRUD frais inscription

POST   /api/v1/annees-scolaires/**      - CRUD années
GET    /api/v1/annees-scolaires/**      - CRUD années
PUT    /api/v1/annees-scolaires/**      - CRUD années
DELETE /api/v1/annees-scolaires/**      - CRUD années
```

### TOUS (SECRETAIRE, DIRECTEUR, SUPER_ADMIN)
```
POST   /api/v1/eleves/**                - Gestion élèves
GET    /api/v1/eleves/**                - Gestion élèves
PUT    /api/v1/eleves/**                - Gestion élèves
DELETE /api/v1/eleves/**                - Gestion élèves

POST   /api/v1/inscriptions/**          - Gestion inscriptions
GET    /api/v1/inscriptions/**          - Gestion inscriptions
PUT    /api/v1/inscriptions/**          - Gestion inscriptions
DELETE /api/v1/inscriptions/**          - Gestion inscriptions

POST   /api/v1/paiements/**             - Gestion paiements
GET    /api/v1/paiements/**             - Gestion paiements
PUT    /api/v1/paiements/**             - Gestion paiements
DELETE /api/v1/paiements/**             - Gestion paiements

POST   /api/v1/blocages/**              - Gestion blocages
GET    /api/v1/blocages/**              - Gestion blocages
DELETE /api/v1/blocages/**              - Gestion blocages

POST   /api/v1/documents/**             - Gestion documents
GET    /api/v1/documents/**             - Gestion documents
PUT    /api/v1/documents/**             - Gestion documents
DELETE /api/v1/documents/**             - Gestion documents

POST   /api/v1/notifications/**         - Gestion notifications
GET    /api/v1/notifications/**         - Gestion notifications
DELETE /api/v1/notifications/**         - Gestion notifications

POST   /api/v1/recus/**                 - Gestion reçus
GET    /api/v1/recus/**                 - Gestion reçus
PUT    /api/v1/recus/**                 - Gestion reçus
DELETE /api/v1/recus/**                 - Gestion reçus
```

---

## 📝 EXEMPLE D'UTILISATION

### 1. Authentification

```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "550e8400-e29b-41d4-a716-446655440000",
    "email": "secretaire@sophia.local",
    "role": "SECRETAIRE"
  }'
```

Réponse:
```json
{
  "token": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1NTBlODQwMC1lMjliLTQxZDQtYTcxNi00NDY2NTU0NDAwMDAiLCJlbWFpbCI6InNlY3JldGFpcmVAc29waGlhLmxvY2FsIiwicm9sZSI6IlNFQ1JFVEFJUkUiLCJpYXQiOjE3NDE1MDAwMDAsImV4cCI6MTc0MTU4NjQwMH0...",
  "expiresIn": 86400,
  "userId": "550e8400-e29b-41d4-a716-446655440000",
  "email": "secretaire@sophia.local",
  "role": "SECRETAIRE"
}
```

### 2. Utiliser le Token

```bash
curl -X GET http://localhost:8080/api/v1/eleves \
  -H "Authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9..."
```

### 3. Valider le Token

```bash
curl -X GET http://localhost:8080/api/v1/auth/validate \
  -H "Authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9..."
```

Réponse:
```json
{
  "valid": true,
  "userId": "550e8400-e29b-41d4-a716-446655440000",
  "email": "secretaire@sophia.local",
  "role": "SECRETAIRE"
}
```

---

## ⚙️ CONFIGURATION (application.properties)

```properties
# JWT Configuration
jwt.secret=sophia-backend-secret-key-2026-very-secure-key-do-not-expose
jwt.expiration=86400000
```

---

## 🔒 Bonnes Pratiques de Sécurité

1. **Ne jamais exposer le JWT en logs**
2. **Toujours utiliser HTTPS en production**
3. **Stocker le secret JWT dans les variables d'environnement**
4. **Implémenter la rotation des secrets**
5. **Valider le token à chaque requête**
6. **Utiliser des tokens courte durée (24h max)**
7. **Implémenter un refresh token pour les durées longues**

---

## 🚨 Codes d'Erreur

| Code | Message | Signification |
|------|---------|---------------|
| 200 | OK | Requête réussie |
| 401 | Unauthorized | Authentification manquante ou invalide |
| 403 | Forbidden | Authentification OK mais autorisations insuffisantes |
| 400 | Bad Request | Requête malformée |
| 500 | Internal Server Error | Erreur serveur |

---

**Status: ✅ JWT ET CONTRÔLE D'ACCÈS IMPLÉMENTÉS**

