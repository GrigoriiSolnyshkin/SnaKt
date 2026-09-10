# SnaKt testing agency diary

Tests in this folder are not part of CI. Each test covers one topic and has a lifetime launch limit of three. Timestamps are UTC.

## Test inventory and execution history

| Area | Test | What it checks | Run limit | Runs used | Last launched | Last result |
| --- | --- | --- | ---: | ---: | --- | --- |
| Nullable types | `test-nullable-conversion.sh` | Conversion of nullable values and null comparisons matches the committed golden | 3 | 3 | 2026-09-10 11:08 | Passed; lifetime limit reached |
| Elvis operator | `test-elvis-conversion.sh` | Conversion of simple, nested, and early-return Elvis expressions matches the committed golden | 3 | 2 | 2026-09-10 11:09 | Passed; one launch remains |
| Recursion | `test-recursion-conversion.sh` | Conversion of a direct recursive call matches the committed golden | 3 | 2 | 2026-09-10 11:09 | Passed; one launch remains |

### 2026-09-10 11:02–11:09 UTC

- Nullable types, launch 1/3: the wrapper was launched with the host JDK 25.0.2. Gradle failed before test execution because this repository's build does not accept that runtime. This was an environment setup failure, not a SnaKt failure.
- Nullable types, launch 2/3: passed under the bundled JDK 21. The repository test driver's fuzzy name filter also selected the uniqueness-checker test named `nullable`, so the wrapper was tightened to an exact generated test selector to preserve its one-topic scope.
- Nullable types, launch 3/3: the exact nullable-types conversion test passed. Do not launch this test again.
- Elvis operator, launches 1/3 and 2/3: conversion passed both times; the second launch used the exact generated test selector.
- Recursion, launches 1/3 and 2/3: conversion passed both times; the second launch used the exact generated test selector.
- Verification was not exercised because Z3 4.8.7 is not configured in this environment. These tests intentionally cover conversion only.

## Bugs found

None recorded.
