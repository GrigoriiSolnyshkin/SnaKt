# Independent verification report

Verified on 2026-09-10. This directory remains isolated from
`formver.compiler-plugin/testData`; no golden file or generated test registration was
added or updated.

## Result

All six implementations implement their collected problem statements. The
LeetCode contracts characterize their results completely. The Codeforces 617A
contract is complete, and the Codeforces 791A contract was strengthened during
this audit to state both the winning year and the failure of the preceding year.

Codeforces 977A's implementation is correct, but its public postcondition only
states the useful safety envelope `0 <= result <= number`; it does not encode the
entire 50-step recurrence. SnaKt's current contract language has no demonstrated
ghost-sequence facility in this repository with which to quantify over an
arbitrary transition trace. Encoding 50 individual states would obscure the
candidate and add substantial solver load. The exact recurrence is nevertheless
present directly in the loop body, and the loop invariant proves nonnegativity,
nonincrease, and exactly `operations` iterations at exit.

## Proof audit

- **CF 617A:** `(distance + 4) / 5` is the positive ceiling of `distance / 5`.
  The input bound prevents overflow; the two postcondition inequalities prove
  feasibility and minimality.
- **CF 791A:** the loop-state disjunction records the exact `3^years` and
  `2^years` multipliers for every possible year from zero through six. The
  source bounds imply a win by year six. The exit guard plus this state relation
  establishes the exhaustive postcondition, including minimality.
- **CF 977A:** each iteration performs exactly the specified `% 10` branch and
  increments `completed` once. Both branches preserve `0 <= current <= number`;
  the bounded counter establishes termination after the requested number of
  transitions. Arithmetic cannot overflow.
- **LC 35:** `[lo, hi)` shrinks on every iteration. The two quantified frontier
  invariants retain all values below the target to the left and all values at
  least the target to the right. At `lo == hi` they are exactly the lower-bound
  postcondition. The midpoint expression is overflow-safe and lies in range.
- **LC 58:** the first loop retains a non-space witness at or before `end`, so
  indexing and decrementing are safe while removing trailing spaces. The second
  loop grows the maximal non-space interval leftward. Choosing the retained
  `end` witnesses every clause of the result postcondition.
- **LC 121:** `minimum` is an attained minimum of the processed prefix and
  `best` is either zero or an attained earlier buy/sell difference dominating
  every processed pair. Updating the candidate before the minimum enforces a
  strictly earlier buy day. The invariants become the complete postcondition at
  loop exit, including singleton inputs.

## Tool-backed checks and limitation

An isolated temporary diagnostic input was prepared for the repository's normal
conversion/verification harness and removed after the probe. The harness could
not start because the environment supplies JBR/OpenJDK 25.0.2; Gradle terminated
before producing any test result with the error `25.0.2`. Consequently this run
cannot claim a successful SnaKt conversion or Silicon solver result.

As independent checks, CF 617A was exhaustively checked over all 1,000,000
allowed distances and CF 791A over every allowed input pair. CF 977A received
boundary cases plus 100,000 randomized input checks against a direct recurrence
oracle. Static proof obligations for all Kotlin files were audited as described
above. A future promotion should rerun conversion and `--verify` under the JDK
version supported by this checkout before moving any candidate into main
`testData`.
