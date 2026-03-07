!/bin/bash
# Script de Vérification et Démarrage du Projet Sophia Backend

echo "🔍 Vérification du Projet Sophia Backend"
echo "========================================"
echo ""

PROJECT_DIR="/home/klaus/Documents/Code/Desktop/SophiaBackend"
cd "$PROJECT_DIR" || exit 1

# 1. Vérifier Maven
echo "1️⃣  Vérification de Maven..."
if command -v mvn &> /dev/null; then
    echo "   ✅ Maven installé"
    mvn -v | grep -E "Apache|version"
else
    echo "   ❌ Maven non trouvé"
    exit 1
fi

echo ""

# 2. Vérifier Java
echo "2️⃣  Vérification de Java..."
if command -v java &> /dev/null; then
    echo "   ✅ Java installé"
    java -version 2>&1 | head -1
else
    echo "   ❌ Java non trouvé"
    exit 1
fi

echo ""

# 3. Compiler le projet
echo "3️⃣  Compilation du projet..."
mvn clean compile -DskipTests > /tmp/build.log 2>&1

if [ $? -eq 0 ]; then
    echo "   ✅ Compilation réussie"
else
    echo "   ❌ Erreurs de compilation"
    grep "\[ERROR\]" /tmp/build.log | head -10
    exit 1
fi

echo ""

# 4. Résumé
echo "✅ Toutes les vérifications sont passées!"
echo ""
echo "📋 Fichiers corrigés:"
echo "   - 8 Repository Interfaces"
echo "   - 7 Repository Adapters"
echo "   - 4 Services"
echo ""
echo "🚀 Pour démarrer l'application:"
echo "   mvn spring-boot:run"
echo ""
echo "🌐 L'API sera disponible sur:"
echo "   http://localhost:8080/api/v1/"

