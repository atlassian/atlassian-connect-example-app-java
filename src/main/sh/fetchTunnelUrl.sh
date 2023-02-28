#!/bin/sh

if ! command -v jq &> /dev/null
then
    echo "Missing `jq`! Please install jq"
    exit 1
fi

baseUrl=$(curl --silent http://127.0.0.1:4040/api/tunnels | jq -r '.tunnels[] | select(.proto == "https") | .public_url')

DIR=$(dirname "$0")
ENV_FILE="${DIR}/../../../.env"
CONNECT_FILE="${DIR}/../../../atlassian-connect.json"

sed -i '' "s|APP_URL=|APP_URL=${baseUrl}|g" $ENV_FILE
echo "APP URL updated in the env file"

sed -i '' "s|{{baseUrl}}|${baseUrl}|g" $CONNECT_FILE
echo "Base URL updated in the atlassian connect file"
