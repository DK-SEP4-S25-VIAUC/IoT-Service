#!/bin/bash

# 🟡 Brug: ./deploy-tcp.sh [tag]
# Eksempel: ./deploy-tcp.sh dev

TAG=${1:-main}

APP_NAME=tcp-server
RESOURCE_GROUP=SEP4
ACR_NAME=alperenacr123
ACR_IMAGE=$ACR_NAME.azurecr.io/tcp-socket-server:$TAG
LOCAL_IMAGE=alperen941/tcp-socket-server:$TAG

echo ""
echo "Henter image: $LOCAL_IMAGE"
docker pull $LOCAL_IMAGE

echo "Logger ind på Azure og ACR..."
az account show > /dev/null 2>&1 || az login
az acr login --name $ACR_NAME

echo "Tagger image til ACR: $ACR_IMAGE"
docker tag $LOCAL_IMAGE $ACR_IMAGE

echo "Pusher image til ACR..."
docker push $ACR_IMAGE

echo "Sletter eksisterende container (hvis den findes)..."
az container delete --name $APP_NAME --resource-group $RESOURCE_GROUP --yes --no-wait
sleep 5

echo "Opretter ny container med image fra ACR..."
az container create \
  --resource-group $RESOURCE_GROUP \
  --name $APP_NAME \
  --image $ACR_IMAGE \
  --cpu 1 \
  --memory 1.5 \
  --ports 5000 \
  --os-type Linux \
  --restart-policy OnFailure \
  --ip-address Public

echo ""
echo "✅ Deploy færdig!"
