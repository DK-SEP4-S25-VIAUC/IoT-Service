#!/bin/bash

# 🟡 Brug: ./deploy.sh [tag]
# Eksempel: ./deploy.sh main

# Hvis intet tag er angivet, brug 'main' som standard
TAG=${1:-main}

# 📦 Sæt navn på din container app og resource group
APP_NAME=iot-service-api
RESOURCE_GROUP=SEP4

# 🔗 Docker image tag
IMAGE=alperen941/iot-springboot:$TAG

echo ""
echo "🚀 Deployer image: $IMAGE"
echo "📦 Container App: $APP_NAME"
echo "📂 Resource Group: $RESOURCE_GROUP"
echo ""

# ✅ Sørg for du er logget ind
az account show > /dev/null 2>&1 || az login

# 🔄 Udfør deploy
az containerapp update   --name $APP_NAME   --resource-group $RESOURCE_GROUP   --image $IMAGE

echo ""
echo "✅ Deploy færdig!"
