# API SOPHIA BACKEND — Endpoints complets (UUID-only)

## Table des matières
1. [Années scolaires](#années-scolaires)
2. [Cycles](#cycles)
3. [Niveaux](#niveaux)
4. [Élèves](#élèves)
5. [Inscriptions](#inscriptions)
6. [Frais scolaires](#frais-scolaires)
7. [Frais divers](#frais-divers)
8. [Paiements](#paiements)
9. [Reçus](#reçus)
10. [Notifications](#notifications)
11. [Logs](#logs)

> Tous les identifiants exposés sont des UUID. Les champs `uuid` des ressources sont en lecture seule et ne doivent pas être envoyés dans les requêtes de création/mise à jour.

---

## Années scolaires

### Créer
`POST /api/v1/annees-scolaires`
```json
{
  "libelle": "2025-2026",
  "dateDebut": "2025-09-01",
  "dateFin": "2026-06-30"
}
```
Réponse 201
```json
{
  "uuid": "b8c4d5e6-f7a8-49b0-c1d2-e3f4a5b6c7d8",
  "libelle": "2025-2026",
  "dateDebut": "2025-09-01",
  "dateFin": "2026-06-30",
  "estActive": true
}
```

### Lister
`GET /api/v1/annees-scolaires`
Réponse 200: tableau d'objets année scolaire (même structure que ci-dessus).

### Détails
`GET /api/v1/annees-scolaires/{uuid}`

### Année active
`GET /api/v1/annees-scolaires/active`

### Mettre à jour
`PUT /api/v1/annees-scolaires/{uuid}`
Body identique au POST.

### Supprimer
`DELETE /api/v1/annees-scolaires/{uuid}` (204)

---

## Cycles

### Créer
`POST /api/v1/cycles`
```json
{
  "nom": "Primaire",
  "description": "Cycle primaire (CP à CM2)",
  "ordre": 1
}
```
Réponse 201
```json
{
  "uuid": "b8c4d5e6-f7a8-49b0-c1d2-e3f4a5b6c7d8",
  "nom": "Primaire",
  "description": "Cycle primaire (CP à CM2)",
  "ordre": 1
}
```

### Lister
`GET /api/v1/cycles`

### Détails
`GET /api/v1/cycles/{uuid}`

### Mettre à jour
`PUT /api/v1/cycles/{uuid}`
Body identique au POST.

### Supprimer
`DELETE /api/v1/cycles/{uuid}` (204)

---

## Niveaux

### Créer
`POST /api/v1/niveaux`
```json
{
  "nom": "6ème",
  "cycleUuid": "c9d5e6f7-a8b9-50c1-d2e3-f4a5b6c7d8e9",
  "ordre": 1
}
```
Réponse 201
```json
{
  "uuid": "a1b2c3d4-e5f6-47a8-b9c0-d1e2f3a4b5c6",
  "nom": "6ème",
  "cycleUuid": "c9d5e6f7-a8b9-50c1-d2e3-f4a5b6c7d8e9",
  "ordre": 1
}
```

### Lister
`GET /api/v1/niveaux`

### Par cycle
`GET /api/v1/niveaux/cycle/{cycleUuid}`

### Détails
`GET /api/v1/niveaux/{uuid}`

### Mettre à jour
`PUT /api/v1/niveaux/{uuid}`
Body identique au POST.

### Supprimer
`DELETE /api/v1/niveaux/{uuid}` (204)

---

## Élèves

### Créer
`POST /api/v1/eleves`
```json
{
  "matricule": "ELV-2026-001",
  "nom": "Doe",
  "prenom": "Jane",
  "dateNaissance": "2012-05-10",
  "lieuNaissance": "Paris",
  "sexe": "FEMININ",
  "nationalite": "FR",
  "adresse": "12 rue Exemple",
  "nomTuteur": "Doe John",
  "telephoneTuteur": "+33102030405",
  "emailTuteur": "john.doe@mail.com",
  "photoFournie": true,
  "acteNaissanceFourni": true,
  "certificatResidenceFourni": false,
  "bulletinsFournis": true,
  "statutDossier": "EN_COURS",
  "dateCreationDossier": "2026-03-20"
}
```

### Lister
`GET /api/v1/eleves`

### Détails
`GET /api/v1/eleves/{uuid}`

### Mettre à jour
`PUT /api/v1/eleves/{uuid}`
Body identique au POST.

### Archiver
`POST /api/v1/eleves/{uuid}/archiver` (200)

---

## Inscriptions

### Créer
`POST /api/v1/inscriptions`
```json
{
  "eleveUuid": "2f5d3f0c-8f6e-4f2c-9b1c-123456789abc",
  "niveauUuid": "a1b2c3d4-e5f6-47a8-b9c0-d1e2f3a4b5c6",
  "anneeScolaireUuid": "b8c4d5e6-f7a8-49b0-c1d2-e3f4a5b6c7d8",
  "dateInscription": "2026-09-01",
  "statut": "DEPOT",
  "commentaire": "Dossier déposé",
  "bloqueExamen": false,
  "bloqueEvaluation": false,
  "raisonBlocage": null
}
```
Réponse 201: objet inscription avec `uuid` + timestamps éventuels.

### Lister
`GET /api/v1/inscriptions`

### Détails
`GET /api/v1/inscriptions/{uuid}`

### Valider les pièces
`PUT /api/v1/inscriptions/{uuid}/valider-pieces` (200) — pas de body.

### Paiement inscription
`POST /api/v1/inscriptions/{uuid}/paiement`
```json
{
  "typePaiement": "INSCRIPTION",
  "montant": 5000,
  "modePaiement": "ESPECES",
  "commentaire": "Frais d'inscription"
}
```
Réponse 200: inscription mise à jour.

---

## Frais scolaires

### Créer
`POST /api/v1/frais-scolaires`
```json
{
  "niveauUuid": "a1b2c3d4-e5f6-47a8-b9c0-d1e2f3a4b5c6",
  "anneeScolaireUuid": "b8c4d5e6-f7a8-49b0-c1d2-e3f4a5b6c7d8",
  "montantTotal": 150000,
  "description": "Frais annuels 2025-2026"
}
```

### Lister
`GET /api/v1/frais-scolaires`

### Par année scolaire
`GET /api/v1/frais-scolaires/annee/{anneUuid}`

### Détails
`GET /api/v1/frais-scolaires/{uuid}`

### Mettre à jour
`PUT /api/v1/frais-scolaires/{uuid}`
Body identique au POST.

### Supprimer
`DELETE /api/v1/frais-scolaires/{uuid}` (204)

---

## Frais divers

### Créer
`POST /api/v1/frais-divers`
```json
{
  "niveauUuid": "a1b2c3d4-e5f6-47a8-b9c0-d1e2f3a4b5c6",
  "anneeScolaireUuid": "b8c4d5e6-f7a8-49b0-c1d2-e3f4a5b6c7d8",
  "description": "Frais d'examen",
  "montant": 10000
}
```

### Lister
`GET /api/v1/frais-divers`

### Par année scolaire
`GET /api/v1/frais-divers/annee/{anneUuid}`

### Détails
`GET /api/v1/frais-divers/{uuid}`

### Mettre à jour
`PUT /api/v1/frais-divers/{uuid}`
Body identique au POST.

### Supprimer
`DELETE /api/v1/frais-divers/{uuid}` (204)

---

## Paiements

TypePaiement: `INSCRIPTION`, `SCOLARITE`, `DIVERS` (pas de `FRAIS_*`).
ModePaiement: `ESPECES`, `CHEQUE`, `VIREMENT`, `AUTRE`.

### Enregistrer un paiement
`POST /api/v1/paiements`
```json
{
  "inscriptionUuid": "2f5d3f0c-8f6e-4f2c-9b1c-123456789abc",
  "typePaiement": "SCOLARITE",
  "referenceUuid": "9f8e7d6c-5b4a-3a2b-1c0d-123456789abc", // tranche ou frais divers selon type
  "montant": 50000,
  "datePaiement": "2026-10-10T09:30:00",
  "modePaiement": "ESPECES",
  "commentaire": "1ère tranche"
}
```
Réponse 201: objet paiement avec `uuid` et champs en lecture seule.

### Lister
`GET /api/v1/paiements`

### Détails
`GET /api/v1/paiements/{uuid}`

### Historique par inscription
`GET /api/v1/paiements/inscription/{inscriptionUuid}`

### Échéancier par inscription
`GET /api/v1/paiements/inscription/{inscriptionUuid}/echeancier`

### Solde restant
`GET /api/v1/paiements/inscription/{inscriptionUuid}/solde-restant?montantTotal=150000`
Réponse 200: nombre décimal.

### Vérifier si à jour
`GET /api/v1/paiements/inscription/{inscriptionUuid}/a-jour?montantTotal=150000`
Réponse 200: `true` ou `false`.

### Impayés d'une inscription
`GET /api/v1/paiements/inscription/{inscriptionUuid}/impayes`

### Élèves en retard
`GET /api/v1/paiements/eleves-en-retard`

### Filtrer par statut
`GET /api/v1/paiements/eleves/statut/{statut}`

### Mettre à jour
`PUT /api/v1/paiements/{uuid}`
Body identique au POST (sans `uuid`).

### Supprimer
`DELETE /api/v1/paiements/{uuid}` (204)

---

## Reçus

### Informations pour générer un reçu
`GET /api/v1/recus/{paiementUuid}`
Réponse 200 (`InfoRecuDTO`):
```json
{
  "paiementUuid": "9f8e7d6c-5b4a-3a2b-1c0d-123456789abc",
  "montant": 50000,
  "datePaiement": "2026-10-10T09:30:00",
  "modePaiement": "ESPECES",
  "typePaiement": "SCOLARITE",
  "commentaire": "1ère tranche",
  "matriculeEleve": "ELV-2026-001",
  "nomEleve": "Doe",
  "prenomEleve": "Jane",
  "niveau": "6ème",
  "anneeScolaire": "2025-2026",
  "nomTranche": "Tranche 1",
  "montantTranche": 50000,
  "libelleFrais": null,
  "montantFrais": null
}
```

---

## Notifications

TypeNotification: `DEMANDE_RECU`, `RECU_DISPONIBLE`, `ALERTE_IMPAYES`.

### Créer
`POST /api/v1/notifications`
```json
{
  "type": "RECU_DISPONIBLE",
  "expediteurUuid": "1c2d3e4f-5678-90ab-cdef-1234567890ab",
  "destinataireUuid": "2f5d3f0c-8f6e-4f2c-9b1c-123456789abc",
  "contenu": "Votre reçu est disponible",
  "lu": false,
  "lien": "/recus/9f8e7d6c-5b4a-3a2b-1c0d-123456789abc"
}
```
Réponse 200: objet notification avec `uuid` et `dateCreation`.

### Lister
`GET /api/v1/notifications`

### Non lues pour un destinataire
`GET /api/v1/notifications/non-lues?destinataireUuid={uuid}`

### Détails
`GET /api/v1/notifications/{uuid}`

### Supprimer
`DELETE /api/v1/notifications/{uuid}` (204)

---

## Logs

ActionLog (valeurs possibles à vérifier dans le code, ex: CREATE, UPDATE, DELETE, LOGIN, LOGOUT).

### Lister
`GET /api/v1/logs`

### Détails
`GET /api/v1/logs/{uuid}`

### Par utilisateur
`GET /api/v1/logs/utilisateur/{utilisateurUuid}`

### Par entité
`GET /api/v1/logs/entite/{entite}/{entiteId}`

### Par action
`GET /api/v1/logs/action/{action}`

### Supprimer
`DELETE /api/v1/logs/{uuid}` (204)
