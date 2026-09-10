# Additional scouting test data

This directory contains problem statements and candidate solutions collected by
the scouting automation. It is intentionally outside `testData`, is not read by
`GenerateTests`, and does not add to the normal test or verification runtime.

Each problem directory contains:

- `problem.md`: a paraphrased statement, source link, constraints, and intended
  pure Kotlin API;
- `solution.kt`: a candidate implementation with FormVer contracts and, where
  applicable, loop invariants.

## Verification status

The implementations were independently audited for functional correctness,
termination, edge cases, and integer overflow under their stated constraints.
They have not been added to the generated test suite or given golden files.

`codeforces/4A/solution.kt` uses a direct, non-recursive contract and is expected
to be suitable for backend verification. The other three solutions express
their exact loop invariants through recursive `@Pure` specification helpers.
The current verifier requires a well-foundedness `decreases` clause for such
self-referential postconditions, but the FormVer DSL does not currently expose
one. Those files are therefore useful scouting candidates, not claims of a
successful backend proof.
