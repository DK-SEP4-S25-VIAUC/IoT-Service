#!/bin/bash

# 🟡 Brug: ./deploy-tcp.sh [tag]
# Eksempel: ./deploy-tcp.sh main

TAG=${1:-main}

APP_NAME=tcp-server
RESOURCE_GROUP=SEP4
IMAGE=alperen941/tcp-socket-server:$TAG

echo ""
echo "🚀 Deployer image: $IMAGE"
echo "📦 Container Instance: $APP_NAME"
echo "📂 Resource Group: $RESOURCE_GROUP"
echo ""

# ✅ Tjek Azure login
az account show > /dev/null 2>&1 || az login

# 🧹 Slet eksisterende container (hvis den findes)
echo "🧹 Sletter eksisterende container (hvis den findes)..."
az container delete --name $APP_NAME --resource-group $RESOURCE_GROUP --yes --no-wait

# ⏳ Vent lidt før ny deploy (valgfrit delay)
sleep 5

# 🚀 Genopret container med opdateret image
echo "🔁 Opretter ny container med image $IMAGE ..."
az container create \
  --resource-group $RESOURCE_GROUP \
  --name $APP_NAME \
  --image $IMAGE \
  --cpu 1 \
  --memory 1.5 \
  --ports 5000 \
  --os-type Linux \
  --restart-policy OnFailure \
  --ip-address Public

echo ""
echo "✅ Deploy færdig!"