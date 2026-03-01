# Architecture SophiaBackend

## Vue d'ensemble

SophiaBackend suit une **architecture hexagonale (Clean Architecture)** avec une séparation claire des responsabilités. Cette approche garantit une maintenabilité élevée, une testabilité optimale et une indépendance vis-à-vis des frameworks.

## Structure des dossiers

```
com.sophia.backend
├── Application.java (point d'entrée Spring Boot)
│
├── domain               (couche métier / cœur)
│   ├── model            (entités métier, value objects, énumérations)
│   │   ├── Etablissement.java
│   │   ├── Eleve.java
│   │   ├── ... (toutes les entités)
│   │   └── enums        (tous les enums)
│   │       ├── Statut.java
│   │       └── ...
│   └── repository       (interfaces des repositories)
│       ├── EtablissementRepository.java
│       ├── EleveRepository.java
│       └── ...
│
├── application          (couche applicative / cas d'utilisation)
│   ├── service          (orchestration de la logique métier)
│   │   ├── GestionInscriptionService.java
│   │   ├── PaiementService.java
│   │   └── ...
│   └── dto              (objets de transfert pour les cas d'usage)
│       ├── InscriptionDTO.java
│       └── ...
│
├── infrastructure       (détails techniques : BD, sécurité, etc.)
│   ├── persistence      (implémentation JPA des repositories)
│   │   ├── entity       (entités JPA - mappées à la base de données)
│   │   │   ├── EtablissementEntity.java
│   │   │   ├── EleveEntity.java
│   │   │   └── ...
│   │   ├── mapper       (conversion entre domain et JPA si nécessaire)
│   │   │   └── EtablissementMapper.java
│   │   └── repository   (implémentations concrètes des interfaces domain)
│   │       ├── EtablissementRepositoryImpl.java
│   │       └── ...
│   ├── security         (JWT, filtres, UserDetailsService)
│   │   ├── JwtTokenProvider.java
│   │   ├── SecurityConfig.java
│   │   └── CustomUserDetailsService.java
│   ├── config           (configurations Spring)
│   │   ├── WebConfig.java (CORS, etc.)
│   │   ├── WebSocketConfig.java
│   │   └── ...
│   └── service          (services techniques)
│       ├── EmailService.java
│       ├── PdfGeneratorService.java
│       └── ...
│
└── interfaces           (couche de présentation : contrôleurs REST)
    ├── web              (API endpoints)
    │   ├── controller
    │   │   ├── EleveController.java
    │   │   ├── PaiementController.java
    │   │   ├── EtablissementController.java
    │   │   └── ...
    │   └── dto          (DTO spécifiques aux requêtes/réponses HTTP)
    │       ├── CreateEleveRequest.java
    │       ├── EleveResponse.java
    │       └── ...
    └── listener         (gestionnaires d'événements/WebSocket)
        ├── NotificationListener.java
        └── ...
```

---

## Détails des couches

### 1. **Domain (Couche Métier)**

C'est le cœur de l'application, **complètement indépendante de Spring et des frameworks**.

#### `domain/model`
- **Entités métier** : Classes représentant les concepts clés du domaine (Eleve, Etablissement, etc.)
- **Value Objects** : Objets immuables représentant des valeurs (Adresse, Email, etc.)
- **Enums** : Énumérations métier (Statut, Role, TypePaiement, etc.)

**Caractéristiques** :
- Pas de dépendances vers Spring
- Logique métier pure
- Encapsulation des règles de gestion

**Exemple** :
```java
public class Eleve {
    private String id;
    private String nom;
    private String email;
    private Statut statut;
    
    // Constructeur, getters/setters
    // Logique métier : validation, conversion d'état, etc.
}
```

#### `domain/repository`
- **Interfaces** définissant les contrats de persistance
- Orientées vers le métier, pas vers la technique JPA
- Permettent l'inversion de dépendance (DIP)

**Exemple** :
```java
public interface EleveRepository {
    Optional<Eleve> findById(String id);
    List<Eleve> findByStatut(Statut statut);
    void save(Eleve eleve);
    void delete(String id);
}
```

---

### 2. **Application (Couche Applicative)**

Contient les **cas d'utilisation** et **l'orchestration** de la logique métier.

#### `application/service`
- **Services applicatifs** implémentant les cas d'utilisation
- Orchestrent les entités domain et les repositories
- Gèrent les transactions et la coordination
- Appelent les services techniques (email, PDF, etc.)

**Responsabilités** :
- Valider les données d'entrée
- Coordonner les appels aux repositories
- Appliquer les règles métier complexes
- Gérer les transactions
- Émettre des événements domaine

**Exemple** :
```java
@Service
public class GestionInscriptionService {
    private final EleveRepository eleveRepository;
    private final EtablissementRepository etablissementRepository;
    
    public void inscrireEleve(InscriptionDTO dto) {
        // Validation
        // Récupération des données
        // Logique métier
        // Sauvegarde
    }
}
```

#### `application/dto`
- **DTOs applicatifs** : Objets de transfert entre couches
- Contiennent les données du cas d'utilisation
- Découplez la présentation du métier

---

### 3. **Infrastructure (Couche Technique)**

Implémente les détails techniques : persistance, sécurité, communications, etc.

#### `infrastructure/persistence`

##### `entity`
- Entités **JPA/Hibernate** mappées à la base de données
- Souvent identiques aux entités métier (ou mappage simple)
- Annotations JPA (@Entity, @Table, @Column, etc.)

**Exemple** :
```java
@Entity
@Table(name = "eleves")
@Data
public class EleveEntity {
    @Id
    private String id;
    
    @Column(name = "nom", nullable = false)
    private String nom;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private Statut statut;
}
```

##### `mapper`
- Convertit les **EleveEntity → Eleve** (domain) si nécessaire
- Optionnel si les entités domain et JPA sont identiques
- Utile pour les transformations complexes

**Exemple** :
```java
@Component
public class EleveMapper {
    public Eleve entityToDomain(EleveEntity entity) {
        return new Eleve(entity.getId(), entity.getNom(), ...);
    }
    
    public EleveEntity domainToEntity(Eleve eleve) {
        return new EleveEntity(eleve.getId(), eleve.getNom(), ...);
    }
}
```

##### `repository`
- **Implémentations concrètes** des interfaces domain
- Utilisent Spring Data JPA ou Hibernate
- Transforment les requêtes domain en requêtes techniques

**Exemple** :
```java
@Repository
public class EleveRepositoryImpl implements EleveRepository {
    private final JpaEleveRepository jpaRepository;
    
    @Override
    public Optional<Eleve> findById(String id) {
        return jpaRepository.findById(id)
            .map(mapper::entityToDomain);
    }
}
```

#### `security`
- **JWT** : JwtTokenProvider pour générer/valider les tokens
- **Filtres** : JwtAuthenticationFilter pour intercepter les requêtes
- **UserDetailsService** : CustomUserDetailsService pour charger les utilisateurs
- **SecurityConfig** : Configuration Spring Security

**Fichiers typiques** :
- `JwtTokenProvider.java`
- `JwtAuthenticationFilter.java`
- `CustomUserDetailsService.java`
- `SecurityConfig.java`

#### `config`
- **WebConfig** : CORS, intercepteurs, convertisseurs
- **WebSocketConfig** : Configuration WebSocket
- **AsyncConfig** : Configuration asynchrone
- Autres configurations Spring spécifiques

#### `service`
- **Services techniques** : logique sans rapport direct avec le métier
- Exemples : EmailService, PdfGeneratorService, NotificationService
- Appelés par les services applicatifs

---

### 4. **Interfaces (Couche de Présentation)**

Expose l'application via des APIs REST ou WebSocket.

#### `interfaces/web/controller`
- **Contrôleurs REST** : Endpoints HTTP
- Reçoivent les requêtes, retournent les réponses
- Délèguent aux services applicatifs
- Gèrent les codes HTTP et exceptions

**Exemple** :
```java
@RestController
@RequestMapping("/api/eleves")
public class EleveController {
    private final GestionInscriptionService service;
    
    @PostMapping
    public ResponseEntity<EleveResponse> creerEleve(@RequestBody CreateEleveRequest request) {
        // Appel au service applicatif
        // Transformation en response
        // Retour HTTP
    }
}
```

#### `interfaces/web/dto`
- **DTOs de requête** : CreateEleveRequest, UpdateEleveRequest
- **DTOs de réponse** : EleveResponse, ListElevesResponse
- **Spécifiques à HTTP** : codes status, en-têtes, etc.

#### `interfaces/listener`
- **Gestionnaires d'événements** : WebSocket, event listeners
- Notifications en temps réel
- Pubsub pour les changements

---

## Flux de données

```
Client HTTP
    ↓
[EleveController] (interfaces/web/controller)
    ↓
[CreateEleveRequest] (interfaces/web/dto)
    ↓
[GestionInscriptionService] (application/service)
    ↓
[Eleve] (domain/model) + [EleveRepository] (domain/repository)
    ↓
[EleveRepositoryImpl] (infrastructure/persistence/repository)
    ↓
[EleveEntity] + [JpaEleveRepository] (infrastructure/persistence)
    ↓
[Database] (PostgreSQL, MySQL, etc.)
    ↑
[Mapper] (infrastructure/persistence/mapper)
    ↑
[EleveResponse] (interfaces/web/dto)
    ↑
Client HTTP
```

---

## Principes appliqués

### 1. **Separation of Concerns (SoC)**
Chaque couche a une responsabilité unique et bien définie.

### 2. **Dependency Inversion Principle (DIP)**
Les couches hautes dépendent des abstractions (interfaces), pas des implémentations.

```
interfaces → application → domain ← infrastructure
                ↓
            repositories (interfaces)
                ↑
         repository implementations
```

### 3. **Domain-Driven Design (DDD)**
Le domaine métier est au cœur, complètement indépendant de la technique.

### 4. **Testabilité**
Chaque couche peut être testée indépendamment :
- **Domain** : tests unitaires purs
- **Application** : tests unitaires + mock repositories
- **Infrastructure** : tests d'intégration
- **Interfaces** : tests de contrôleurs

---

## Règles de dépendance

| De | Vers | Autorisé ? |
|---|---|---|
| Domain | Quelconque | ❌ Non (isolé) |
| Application | Domain | ✅ Oui |
| Application | Infrastructure | ❌ Non (via interfaces) |
| Infrastructure | Domain | ✅ Oui |
| Infrastructure | Application | ❌ Non |
| Interfaces | Application | ✅ Oui |
| Interfaces | Domain | ✅ Oui (via application) |
| Interfaces | Infrastructure | ❌ Non (via application) |

---

## Exemple complet : Cas d'usage "Inscrire un élève"

### 1. **Controller** (interfaces/web/controller)
```java
@PostMapping("/eleves")
public ResponseEntity<EleveResponse> inscrireEleve(
    @RequestBody CreateEleveRequest request) {
    EleveDTO result = service.inscrireEleve(request);
    return ResponseEntity.ok(new EleveResponse(result));
}
```

### 2. **Service applicatif** (application/service)
```java
public EleveDTO inscrireEleve(CreateEleveRequest request) {
    // Validation
    validateEmail(request.getEmail());
    
    // Création du domaine
    Eleve eleve = new Eleve(request.getNom(), request.getEmail());
    
    // Appel repository
    eleveRepository.save(eleve);
    
    // Retour DTO
    return mapToDTO(eleve);
}
```

### 3. **Repository** (infrastructure/persistence)
```java
public void save(Eleve eleve) {
    EleveEntity entity = mapper.domainToEntity(eleve);
    jpaRepository.save(entity);
}
```

### 4. **Base de données**
L'entité est persistée via JPA.

---

## Bonnes pratiques

1. **Nommage clair** : les noms reflètent la responsabilité
   - `*Service` : services applicatifs
   - `*Repository` : interfaces de persistance
   - `*RepositoryImpl` : implémentations
   - `*Controller` : contrôleurs REST
   - `*Entity` : entités JPA
   - `*DTO` / `*Request` / `*Response` : objets de transfert

2. **Pas de logic dans les getters/setters**

3. **Immutabilité** pour les value objects et DTOs (Lombok `@Value`)

4. **Validation** au niveau du domaine et du contrôleur

5. **Logging** : chaque couche log ses opérations importantes

6. **Gestion des erreurs** : exceptions domaine + mappage HTTP

---

## Outils et frameworks utilisés

| Couche | Framework | Rôle |
|---|---|---|
| Domain | Aucun | Métier pur |
| Application | Spring Framework | Injection de dépendances |
| Infrastructure | Spring Data JPA, Hibernate | Persistance |
| Infrastructure | Spring Security | Authentification/Autorisation |
| Interfaces | Spring Web MVC | Contrôleurs REST |
| Tous | Lombok | Réduction de boilerplate |
| Tous | Junit 5, Mockito | Tests |

---

## Conclusion

Cette architecture garantit :
- ✅ **Flexibilité** : changement facile de framework/BD
- ✅ **Testabilité** : chaque couche testée indépendamment
- ✅ **Maintenabilité** : code clair et organisé
- ✅ **Scalabilité** : croissance sans dégradation
- ✅ **Professionnalisme** : standards de l'industrie


