#!/usr/bin/env bash
set -euo pipefail

repo_root=$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)
fixture="$repo_root/formver.compiler-plugin/testData/diagnostics/verification/user_invariants/exists.kt"
golden="$repo_root/formver.compiler-plugin/testData/diagnostics/verification/user_invariants/exists.fir.diag.txt"

# Topic invariant: the probe exercises exists<T> and its reviewed conversion
# must contain Viper's existential binder. These assertions keep a mistakenly
# regenerated but semantically empty golden from masking a regression.
grep -Fq 'exists<Int>' "$fixture"
grep -Fq '(exists ' "$golden"

cd "$repo_root"
./agent-scripts/test.sh "$fixture"
