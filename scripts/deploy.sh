#!/usr/bin/env bash
set -euo pipefail

echo "Building redemption-service..."
mvn clean package -DskipTests

echo "Deploying to Railway..."
railway up
