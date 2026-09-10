# Testing agency diary

This diary tracks manual, non-CI test launches. A launch count includes an
attempt that was blocked before the test body ran; blocked tests are not
retried during the same scheduled run.

## Coverage

### Elvis operator conversion

- SnaKt area: compiler-plugin conversion of nullable Elvis expressions.
- Test: `tests/elvis-operator-conversion.sh` selects only `elvis.kt` through
  the repository test driver.
- Last launch: 2026-09-10T13:01:00Z (1 launch total).
- Result: BLOCKED before test execution. Gradle rejected Java 25.0.2 with
  `What went wrong: 25.0.2`; neither compiler nor locality produced results.
- Bugs found: none. The environment failure does not establish a SnaKt defect.

### Safe-call operator conversion

- SnaKt area: compiler-plugin conversion of nullable safe-call expressions.
- Test: `tests/safe-call-conversion.sh` selects only `safe_call.kt` through
  the repository test driver.
- Last launch: 2026-09-10T13:02:00Z (1 launch total).
- Result: BLOCKED before test execution. Gradle rejected Java 25.0.2 with
  `What went wrong: 25.0.2`; neither compiler nor locality produced results.
- Bugs found: none. The environment failure does not establish a SnaKt defect.

## Candidate topics for later runs

Each candidate remains a separate topic and should receive its own test.

- Identity equality conversion.
- Integer arithmetic operator conversion.
- Nullable casts.
- `when` control-flow conversion.
- Loop invariant verification.
- Preconditions on member functions.
- Postconditions involving old values.
- Universal quantifier triggers.
- Function overload resolution.
- Secondary-constructor verification.

## Bug log

No SnaKt bugs have been found by this testing agency as of
2026-09-10T13:03:31Z. Consequently, no `bugFound` issue was created.
