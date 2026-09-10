#!/usr/bin/env bash
# Manual agency test: run at most when its diary entry schedules it; never from CI.

set -euo pipefail

TEST_DIR="$(cd "$(dirname "$0")" && pwd)"
REPO_ROOT="$(cd "$TEST_DIR/.." && pwd)"

output_file="$(mktemp)"
trap 'rm -f "$output_file"' EXIT

if ! "$REPO_ROOT/agent-scripts/test.sh" \
    formver.compiler-plugin/testData/diagnostics/expensive_verification/algorithms/max_character.kt \
    | tee "$output_file"; then
    echo "existential quantifier conversion test failed" >&2
    exit 1
fi

if ! grep -Eq '^Ran [1-9][0-9]* tests, [1-9][0-9]* passed, 0 failed\.$' "$output_file"; then
    echo "test runner did not report a clean, non-empty existential quantifier conversion run" >&2
    exit 1
fi
