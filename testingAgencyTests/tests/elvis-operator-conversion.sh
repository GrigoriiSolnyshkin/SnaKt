#!/usr/bin/env bash
set -euo pipefail

AGENCY_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
REPOSITORY_DIR="$(cd "$AGENCY_DIR/.." && pwd)"

cd "$REPOSITORY_DIR"
exec ./agent-scripts/test.sh \
    formver.compiler-plugin/testData/diagnostics/verification/operators/elvis.kt
