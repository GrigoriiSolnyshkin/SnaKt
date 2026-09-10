#!/usr/bin/env bash
# Topic: conversion of a recursive function call. Lifetime launch limit: 3.
set -euo pipefail

REPO_ROOT="$(cd "$(dirname "$0")/.." && pwd)"
cd "$REPO_ROOT"
exec ./gradlew :formver.compiler-plugin:untilConversion --rerun --no-daemon -q \
    --tests 'org.jetbrains.kotlin.formver.plugin.runners.PhasedDiagnosticTestGenerated$Verification$Control_flow.testRecursion'
