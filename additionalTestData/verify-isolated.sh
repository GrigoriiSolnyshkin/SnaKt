#!/usr/bin/env bash
# Compile and verify only additionalTestData cases without registering them in
# the repository's normal testData tree. Use --refresh after intentional edits.

set -euo pipefail

repo_root="$(cd "$(dirname "$0")/.." && pwd)"
stage="$repo_root/formver.compiler-plugin/testData/diagnostics/additional_automation"
generated="$repo_root/formver.compiler-plugin/test-gen/org/jetbrains/kotlin/formver/plugin/runners/PhasedDiagnosticTestGenerated.java"

java_version="$(java -version 2>&1 | sed -n 's/.*version "\([0-9][0-9]*\).*/\1/p')"
if [[ -z "$java_version" || "$java_version" -gt 24 ]]; then
    echo "This repository's Kotlin toolchain cannot run on the active JDK (${java_version:-unknown}); use JDK 17." >&2
    exit 2
fi

if ! command -v "${Z3_EXE:-z3}" >/dev/null 2>&1; then
    echo "SnaKt verification requires Z3; install z3 or set Z3_EXE to its executable." >&2
    exit 2
fi

if [[ $# -gt 1 || ( $# -eq 1 && "$1" != "--refresh" ) ]]; then
    echo "Usage: $0 [--refresh]" >&2
    exit 2
fi

if [[ -e "$stage" ]]; then
    echo "Refusing to overwrite existing staging path: $stage" >&2
    exit 1
fi

backup="$(mktemp)"
cp "$generated" "$backup"
cleanup() {
    cp "$backup" "$generated"
    rm -f "$backup"
    find "$stage" -type f -delete 2>/dev/null || true
    rmdir "$stage" 2>/dev/null || true
}
trap cleanup EXIT

mkdir "$stage"

stage_case() {
    local source="$1" base="$2"
    # VIPER_TEXT is a Kotlin test-framework expectation marker, not source
    # syntax. Keep canonical solutions compilable and add markers only here.
    sed -E \
        -e 's/fun canSplitWatermelon/fun <!VIPER_TEXT!>canSplitWatermelon<!>/' \
        -e 's/fun decimalDigitsReadSame/fun <!VIPER_TEXT!>decimalDigitsReadSame<!>/' \
        -e 's/fun isPalindrome/fun <!VIPER_TEXT!>isPalindrome<!>/' \
        "$source" > "$stage/$base.kt"

    local golden
    for golden in "$repo_root/additionalTestData/goldens/$base".*.txt; do
        if [[ -e "$golden" ]]; then
            cp "$golden" "$stage/"
        fi
    done
}

stage_case "$repo_root/additionalTestData/codeforces/watermelon/Watermelon.kt" watermelon
stage_case "$repo_root/additionalTestData/leetcode/palindrome_number/PalindromeNumber.kt" palindrome_number

if [[ "${1:-}" == "--refresh" ]]; then
    "$repo_root/agent-scripts/test.sh" --update-goldens watermelon
    "$repo_root/agent-scripts/test.sh" --update-goldens palindrome_number
    mkdir -p "$repo_root/additionalTestData/goldens"
    if ! compgen -G "$stage/*.viper.txt" >/dev/null; then
        echo "Verification produced no Viper goldens; inspect the test output above." >&2
        exit 1
    fi
    cp "$stage"/*.viper.txt "$repo_root/additionalTestData/goldens/"
    # A non-empty diagnostics golden means verification failed. Preserve it so
    # it is visible, then fail this command rather than blessing the failure.
    if compgen -G "$stage/*.viper.diag.txt" >/dev/null; then
        cp "$stage"/*.viper.diag.txt "$repo_root/additionalTestData/goldens/"
        if find "$stage" -name '*.viper.diag.txt' -type f -size +0c | grep -q .; then
            echo "Verification diagnostics are non-empty; inspect additionalTestData/goldens." >&2
            exit 1
        fi
    fi
else
    "$repo_root/agent-scripts/test.sh" --verify watermelon
    "$repo_root/agent-scripts/test.sh" --verify palindrome_number
fi
