#!/usr/bin/env bash

set -euo pipefail

readonly test_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
readonly repo_dir="$(cd "$test_dir/.." && pwd)"
readonly count_file="$test_dir/.test-existential-purity.runs"
readonly max_runs=3

run_count=0
if [[ -f "$count_file" ]]; then
    read -r run_count < "$count_file"
fi

if (( run_count >= max_runs )); then
    echo "Run limit reached for existential purity test ($run_count/$max_runs)." >&2
    exit 2
fi

run_count=$((run_count + 1))
printf '%s\n' "$run_count" > "$count_file"

cd "$repo_dir"
./agent-scripts/test.sh --verify exists_list_get_crash
