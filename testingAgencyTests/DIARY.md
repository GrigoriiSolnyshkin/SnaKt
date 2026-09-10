# SnaKt testing agency diary

These exploratory tests are intentionally isolated from the project test suites and CI.
Each script covers one topic and enforces its own lifetime launch limit.

## Test inventory

| Test | SnaKt area and single topic | Launch limit | Last launched | Result | Bug |
| --- | --- | ---: | --- | --- | --- |
| `test-existential-purity.sh` | Existential quantifiers: an impure `List.get` call in an `exists` body is rejected with `PURITY_VIOLATION` rather than crashing the compiler. | 3 (2 launches used) | 2026-09-10 05:00 UTC | Passed under Java 21: expected diagnostic matched and the compiler did not crash. The first launch stopped before test execution because Java 25 is incompatible with the build; the second used a temporary Java 21 runtime and completed. | None observed |

## Candidate topics for later runs

- Existential quantifier conversion in preconditions.
- Existential witnesses in postconditions with trigger-bearing string access.
- Explicit triggers for universal quantifiers.
- Loop invariant preservation across `break` and `continue`.
- Nullable smart casts used in specifications.
- Permission accounting for unique fields.
- Kotlin contract effects with receiver types.
- Integer overflow behavior in arithmetic postconditions.

## Bugs found

None so far.
