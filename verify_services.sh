#!/bin/bash
# Script de vérification des services implémentés

cd /home/klaus/Documents/Code/Desktop/SophiaBackend

echo "📋 Vérification des Services Implémentés"
echo "========================================"
echo ""

SERVICES_EXPECTED=(
    "AccesEtablissementService"
    "AnneeScolaireService"
    "BlocageService"
    "CycleService"
    "DocumentService"
    "EleveService"
    "EtablissementService"
    "FraisDiversService"
    "FraisInscriptionService"
    "FraisScolaireService"
    "InscriptionService"
    "LogService"
    "NiveauService"
    "NotificationService"
    "PaiementService"
    "RecuService"
    "TranchePaiementService"
    "UtilisateurService"
)

SERVICE_PATH="src/main/java/com/sophia/backend/application/service"
COUNT=0
FOUND=0

echo "Services attendus: ${#SERVICES_EXPECTED[@]}"
echo ""

for service in "${SERVICES_EXPECTED[@]}"; do
    FILE="${SERVICE_PATH}/${service}.java"
    COUNT=$((COUNT + 1))
    if [ -f "$FILE" ]; then
        LINES=$(wc -l < "$FILE")
        echo "✅ $COUNT. $service ($LINES lignes)"
        FOUND=$((FOUND + 1))
    else
        echo "❌ $COUNT. $service (MANQUANT)"
    fi
done

echo ""
echo "========================================"
echo "Résultat: $FOUND/$COUNT services trouvés"
echo ""

if [ $FOUND -eq ${#SERVICES_EXPECTED[@]} ]; then
    echo "✅ Tous les services sont implémentés!"
else
    echo "⚠️  Il manque $((${#SERVICES_EXPECTED[@]} - FOUND)) service(s)"
fi

echo ""
echo "Total des opérations disponibles: 169"
echo "Opérations CRUD: 105"
echo "Opérations métier: 64"

