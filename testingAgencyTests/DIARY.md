# SnaKt testing agency diary

These are manual, bounded tests kept outside SnaKt's normal test suites. They are not wired into CI. Each script covers one topic and is launched only when its diary entry is due.

## Test inventory

| Topic | Test | Launch limit | Launches | Last launched (UTC) | Result |
| --- | --- | ---: | ---: | --- | --- |
| Existential quantifier conversion in an algorithm | `existential_quantifier_pipeline.sh` | 3 completed runs total | 1 | 2026-09-10 09:00 | Passed: 1 test, 0 failures |

## Run notes

### 2026-09-10 09:00 UTC — initial test design rejected

- Intended scope: conversion and verification of `exists<T>` in contracts and loop invariants.
- Attempts: one attempt under the default JDK 25 stopped before tests because the build requires JDK 21; a JDK 21 attempt reached the test but verification stopped because `z3` is not installed. No completed SnaKt test is counted for either infrastructure failure.
- Design correction: the `exists.kt` pattern also selects `exists_list_get_crash.kt`, so it does not satisfy the one-topic rule. The script now selects the unique `max_character.kt` fixture and checks existential-quantifier conversion only.
- Bug status: no new SnaKt bug found. The expected Boolean-witness limitation visible in `exists.kt` is already tracked as [issue #297](https://github.com/JetBrains/SnaKt/issues/297) with label `bugFound`.

### 2026-09-10 09:00 UTC — existential quantifier conversion in `max_character`

- Scope: conversion of existential quantifiers used in a postcondition and loop invariant for a maximum-character algorithm.
- Method: run only `max_character.kt` through the conversion pipeline and require a non-empty run with zero failures.
- Status: passed. Exactly 1 test ran and passed; 0 failed. This is completed launch 1 of 3.

## Candidate topics for later bounded runs

Each candidate should become its own script before it is launched.

- Quantifier purity rejection (method calls inside quantifier bodies).
- Quantifier behavior for nullable witness types.
- Nested universal and existential quantifiers.
- Existential quantifiers over Boolean and reference types.
- Trigger inference for string and collection indexing.
- Integer overflow boundaries in verified arithmetic.
- Safe-call and Elvis operator conversion.
- Aliasing and use-after-move diagnostics.
- Loop invariants across `break` and `continue`.
- Gradle plugin target-selection options.

## Automation notes

- `AUTOMATIONS.md` was not present in the checkout on 2026-09-10 09:00 UTC, so no shared automation instructions could be applied.
- `check-all.sh` could not complete in the stock environment because JDK 25 is unsupported and `z3` is absent. With JDK 21 and a temporary Z3 5.1 binary, the full check reached verification but failed the existing `testExists` golden as issue #297's expected warning is no longer produced by that solver version. The focused conversion test above remains green.
