# 🚀 Commandes Utiles - Sophia Backend

## 📦 Compilation et Build

```bash
# Compiler le projet (sans tests)
mvn clean compile -DskipTests

# Compiler et générer le JAR
mvn clean package -DskipTests

# Compiler avec tests
mvn clean verify

# Compiler et installer en local
mvn clean install -DskipTests
```

## 🧪 Tests

```bash
# Lancer tous les tests
mvn test

# Lancer les tests d'une classe
mvn test -Dtest=EleveServiceTest

# Lancer les tests avec coverage
mvn test jacoco:report

# Lancer les tests sans compiler
mvn test -o
```

## 🔍 Analyse et Qualité

```bash
# Vérifier la syntaxe Java
mvn compile

# Analyser le code avec Checkstyle
mvn checkstyle:check

# Rapport complet
mvn clean site

# SonarQube analysis (si configuré)
mvn sonar:sonar
```

## 🌐 Exécution

```bash
# Démarrer l'application Spring Boot
mvn spring-boot:run

# Démarrer avec profil spécifique
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"

# Construire le JAR et le lancer
mvn clean package -DskipTests && java -jar target/sophia-backend-0.0.1-SNAPSHOT.jar
```

## 📊 Inspection du Projet

```bash
# Lister les services disponibles
find src/main/java/com/sophia/backend/application/service -name "*Service.java" | sort

# Lister les contrôleurs
find src/main/java/com/sophia/backend/interfaces/web/controller -name "*Controller.java" | sort

# Lister les entités
find src/main/java/com/sophia/backend/domain/model -name "*.java" | sort

# Lister les repositories
find src/main/java/com/sophia/backend/domain/repository -name "*Repository.java" | sort

# Compter les fichiers Java
find src -name "*.java" -type f | wc -l

# Compter les lignes de code
find src -name "*.java" -type f -exec wc -l {} + | tail -1
```

## 📚 Dépendances

```bash
# Afficher l'arborescence des dépendances
mvn dependency:tree

# Vérifier les dépendances obsolètes
mvn versions:display-dependency-updates

# Vérifier les plugins obsolètes
mvn versions:display-plugin-updates

# Vérifier les vulnérabilités (CVE)
mvn dependency-check:check
```

## 🔧 Nettoyage et Maintenance

```bash
# Nettoyer le répertoire target
mvn clean

# Nettoyer et supprimer les fichiers générés
mvn clean -X

# Mettre à jour les dépendances
mvn versions:update-properties

# Nettoyer le cache Maven
rm -rf ~/.m2/repository
```

## 📝 Documentation et Génération

```bash
# Générer la documentation Javadoc
mvn javadoc:javadoc

# Générer un rapport Maven
mvn site

# Générer les sources (si annotation processors)
mvn clean compile
```

## 🐛 Débogage

```bash
# Compiler avec informations de débogage
mvn clean compile -X

# Lancer avec debug
mvn clean spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"

# Afficher le classpath
mvn dependency:build-classpath -Dmdep.outputFile=classpath.txt
```

## 🚨 Résolution de Problèmes

```bash
# Vider le cache et recompiler
mvn clean && mvn -o compile

# Vérifier les dépendances manquantes
mvn dependency:tree -Dverbose -Dincludes=missing

# Forcer la mise à jour des dépendances
mvn clean install -U -DskipTests

# Afficher les détails de compilation
mvn compile -e -X
```

---

## 📊 Vérification de l'Implémentation

```bash
# Vérifier que tous les services sont présents
echo "Services implémentés:"
find src/main/java/com/sophia/backend/application/service -name "*Service.java" | wc -l
echo "Attendus: 18"

# Vérifier que tous les mappers sont présents
echo ""
echo "Mappers implémentés:"
find src/main/java/com/sophia/backend/infrastructure/persistence/mapper -name "*Mapper.java" | wc -l
echo "Attendus: 25"

# Vérifier que toutes les entités sont présentes
echo ""
echo "Entités domaine:"
find src/main/java/com/sophia/backend/domain/model -name "*.java" | wc -l
echo "Attendus: 18+ (+ Enums)"

# Vérifier que tous les repositories adapters sont présents
echo ""
echo "Repository Adapters:"
find src/main/java/com/sophia/backend/infrastructure/persistence/repository/adapter -name "*Adapter.java" | wc -l
echo "Attendus: 18"
```

---

## 📋 Vérification Rapide du Projet

```bash
#!/bin/bash
# Copier et exécuter ce script

echo "🔍 Vérification Complète du Projet Sophia Backend"
echo "=================================================="

cd /home/klaus/Documents/Code/Desktop/SophiaBackend

# Compilation
echo ""
echo "1️⃣  Compilation..."
mvn -q clean compile -DskipTests && echo "✅ Compilation SUCCESS" || echo "❌ Compilation FAILED"

# Nombre de fichiers
echo ""
echo "2️⃣  Comptage des fichiers..."
JAVA_FILES=$(find src -name "*.java" -type f | wc -l)
echo "   Fichiers Java: $JAVA_FILES"

# Services
echo ""
echo "3️⃣  Services implémentés..."
SERVICES=$(find src/main/java/com/sophia/backend/application/service -name "*Service.java" | wc -l)
echo "   Services: $SERVICES / 18"

# Mappers
echo ""
echo "4️⃣  Mappers implémentés..."
MAPPERS=$(find src/main/java/com/sophia/backend/infrastructure/persistence/mapper -name "*Mapper.java" | wc -l)
echo "   Mappers: $MAPPERS / 25"

# Repository Adapters
echo ""
echo "5️⃣  Repository Adapters..."
ADAPTERS=$(find src/main/java/com/sophia/backend/infrastructure/persistence/repository/adapter -name "*Adapter.java" | wc -l)
echo "   Adapters: $ADAPTERS / 18"

echo ""
echo "=================================================="
echo "✅ Vérification terminée!"
```

---

## 🎯 Opérations Quotidiennes

```bash
# Développement
alias sophia-dev='cd /home/klaus/Documents/Code/Desktop/SophiaBackend && mvn clean compile -DskipTests'
alias sophia-run='cd /home/klaus/Documents/Code/Desktop/SophiaBackend && mvn spring-boot:run'
alias sophia-test='cd /home/klaus/Documents/Code/Desktop/SophiaBackend && mvn test'
alias sophia-build='cd /home/klaus/Documents/Code/Desktop/SophiaBackend && mvn clean package -DskipTests'

# Utilisation
sophia-dev      # Compiler
sophia-run      # Lancer l'app
sophia-test     # Lancer les tests
sophia-build    # Générer le JAR
```

---

## 🔗 Ports et URLs Utiles

```
Application: http://localhost:8080
Swagger API: http://localhost:8080/swagger-ui.html
API Docs: http://localhost:8080/v3/api-docs
Health Check: http://localhost:8080/actuator/health
Metrics: http://localhost:8080/actuator/metrics
```

---

## 📁 Structure des Répertoires Importants

```bash
# Services
src/main/java/com/sophia/backend/application/service/

# Contrôleurs (À créer)
src/main/java/com/sophia/backend/interfaces/web/controller/

# DTOs
src/main/java/com/sophia/backend/application/dto/

# Entités
src/main/java/com/sophia/backend/domain/model/

# Mappers
src/main/java/com/sophia/backend/infrastructure/persistence/mapper/

# Repositories
src/main/java/com/sophia/backend/infrastructure/persistence/repository/

# Entités JPA
src/main/java/com/sophia/backend/infrastructure/persistence/entity/
```

---

## 🎓 Documentation de Référence

- **OPERATIONS_DISPONIBLES.md** - Toutes les 169 opérations
- **GUIDE_RAPIDE_SERVICES.md** - Tableau récapitulatif
- **RESUME_ETAT_PROJET.md** - État complet du projet
- **SERVICES_IMPLEMENTATION.json** - Format JSON structuré
- **ARCHITECTURE.md** - Architecture technique
- **README.md** - Documentation générale

---

**Dernière mise à jour:** 6 Mars 2026

