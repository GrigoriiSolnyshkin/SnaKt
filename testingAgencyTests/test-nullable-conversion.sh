#!/usr/bin/env bash
# Topic: conversion of nullable values and null comparisons. Lifetime launch limit: 3.
set -euo pipefail

REPO_ROOT="$(cd "$(dirname "$0")/.." && pwd)"
cd "$REPO_ROOT"
exec ./gradlew :formver.compiler-plugin:untilConversion --rerun --no-daemon -q \
    --tests 'org.jetbrains.kotlin.formver.plugin.runners.PhasedDiagnosticTestGenerated$Verification$Types.testNullable'
