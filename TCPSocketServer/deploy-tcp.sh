#!/bin/bash

# 🟡 Brug: ./deploy-tcp.sh [tag]
# Eksempel: ./deploy-tcp.sh dev

TAG=${1:-dev}

APP_NAME=tcp-server
RESOURCE_GROUP=SEP4
ACR_NAME=alperenacr123
ACR_LOGIN_SERVER=$ACR_NAME.azurecr.io
IMAGE_NAME=tcp-socket-server
LOCAL_IMAGE=alperen941/$IMAGE_NAME:$TAG
ACR_IMAGE=$ACR_LOGIN_SERVER/$IMAGE_NAME:$TAG

echo ""
echo "🚀 Starter deploy af image: $LOCAL_IMAGE"
echo "📦 ACR destination: $ACR_IMAGE"
echo "📂 Resource Group: $RESOURCE_GROUP"
echo ""

# ✅ Log ind i Azure og Docker (hvis nødvendigt)
az account show > /dev/null 2>&1 || az login
az acr login --name $ACR_NAME

# 🏷️ Tag image til ACR-format
echo "🏷️ Tagger image..."
docker tag $LOCAL_IMAGE $ACR_IMAGE

# 📤 Push image til ACR
echo "📤 Pusher til ACR..."
docker push $ACR_IMAGE

# 🧹 Slet eksisterende container (hvis den findes)
echo "🧹 Sletter eksisterende containerinstans (hvis den findes)..."
az container delete --name $APP_NAME --resource-group $RESOURCE_GROUP --yes --no-wait

# ⏳ Vent lidt før ny deploy
sleep 5

# 🚀 Opret ny containerinstans med ACR image
echo "🔁 Opretter containerinstans fra: $ACR_IMAGE ..."
az container create \
  --resource-group $RESOURCE_GROUP \
  --name $APP_NAME \
  --image $ACR_IMAGE \
  --cpu 1 \
  --memory 1.5 \
  --ports 5000 8081 \
  --os-type Linux \
  --restart-policy OnFailure \
  --ip-address Public \
  --registry-login-server $ACR_LOGIN_SERVER \
  --registry-username $(az acr credential show --name $ACR_NAME --query username -o tsv) \
  --registry-password $(az acr credential show --name $ACR_NAME --query passwords[0].value -o tsv)

echo ""
echo "✅ Deploy færdig! Image: $ACR_IMAGE"
