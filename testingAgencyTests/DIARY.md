# SnaKt testing agency diary

This directory contains opt-in tests only; none are connected to CI. Each test
has one topic and is launched only a limited number of times. Dates are UTC.

## Test inventory

| Topic | Test | Maximum launches | Launches so far | Last launched | Last result |
| --- | --- | ---: | ---: | --- | --- |
| Existential quantifier lowering | `existential_quantifier_lowering.sh` | 3 | 1 | 2026-09-10 03:00 | Infrastructure blocked |
| Existential quantifier trigger lowering | Not created | 3 | 0 | Never | Not run |
| Existential quantifier purity diagnostics | Not created | 3 | 0 | Never | Not run |
| Universal quantifier trigger lowering | Not created | 3 | 0 | Never | Not run |
| Loop invariant preservation | Not created | 3 | 0 | Never | Not run |
| Nullable smart-cast conversion | Not created | 3 | 0 | Never | Not run |
| Integer overflow modelling | Not created | 3 | 0 | Never | Not run |
| Uniqueness use-after-move diagnostics | Not created | 3 | 0 | Never | Not run |
| Non-local return lowering | Not created | 3 | 0 | Never | Not run |
| Gradle plugin option forwarding | Not created | 3 | 0 | Never | Not run |

## Run log

### 2026-09-10 03:00 — existential quantifier lowering

- Area: conversion of the `exists<T>` specification DSL to Viper.
- Test: assert the focused fixture contains an existential specification,
  assert its reviewed conversion contains a Viper existential binder, then run
  the conversion-only golden test for that fixture.
- Launch budget: launch 1 of at most 3.
- Result: infrastructure blocked before test execution. Gradle rejected the
  available Java 25.0.2 runtime; neither compiler nor locality emitted test
  results. The script's shell syntax and semantic preconditions passed. This
  launch still counts against the budget, and it was not retried.
- Bugs found: none. The failure occurred in the test environment before SnaKt
  executed, so no `bugFound` issue was warranted.

## Automation notes

- `AUTOMATIONS.md` was requested but was absent from this checkout and its
  parent workspace at the time of this run. Repository `AGENTS.md` was followed.
