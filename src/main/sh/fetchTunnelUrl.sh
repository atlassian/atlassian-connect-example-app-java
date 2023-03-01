#!/bin/sh

baseUrl=$(curl --silent http://tunnel:4040/api/tunnels | jq -r '.tunnels[] | select(.proto == "https") | .public_url')

echo "NGROK Tunneled URL -------------- $baseUrl"

DIR=$(dirname "$0")
CONNECT_FILE="${DIR}/../../../atlassian-connect.json"

if [ $baseUrl ]; then
  sed -i -E "s|https:\/\/.*ngrok\.io\/?|${baseUrl}|g" $CONNECT_FILE
  echo "Base URL updated in the atlassian connect file"
fi

