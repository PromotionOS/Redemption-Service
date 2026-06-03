#!/usr/bin/env bash
set -euo pipefail

echo "Running redemption-service tests..."
mvn test -pl . "$@"
