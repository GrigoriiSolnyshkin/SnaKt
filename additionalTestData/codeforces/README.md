# Codeforces-derived verification candidates

This directory is staging material only. It is intentionally outside
`formver.compiler-plugin/testData`; none of these files are part of the generated test
suite yet.

The problem summaries below are original paraphrases, not copies of the contest
statements. The linked Codeforces pages are the authoritative provenance for the
problem identifiers and original constraints.

## Selected problems

### CF 617A — Elephant

- Source: <https://codeforces.com/problemset/problem/617/A>
- Candidate function: `minimumSteps(distance: Int): Int`
- Input contract: `1 <= distance <= 1_000_000`.
- Required result: the smallest positive integer `steps` for which
  `distance <= 5 * steps`. Equivalently, an animal starts at position zero and each
  move advances by an integer in `1..5`; return the fewest moves needed to arrive at
  `distance` exactly.
- Useful verification surface: integer arithmetic and a minimality postcondition,
  without collections or I/O.

### CF 791A — Bear and Big Brother

- Source: <https://codeforces.com/problemset/problem/791/A>
- Candidate function: `yearsUntilLarger(limak: Int, bob: Int): Int`
- Input contract: `1 <= limak <= bob <= 10`.
- At the end of each year Limak's current weight is multiplied by three and Bob's by
  two. Return the least positive number of years after which Limak's weight is
  strictly greater than Bob's.
- Required result characterization: for returned `years`, applying the two updates
  `years` times makes Limak heavier, while applying them `years - 1` times does not.
- Useful verification surface: a terminating `while` loop, strict versus non-strict
  comparisons, multiplication, and a least-iteration claim. Under the source bounds
  all intermediate values fit in `Int`.

### CF 977A — Wrong Subtraction

- Source: <https://codeforces.com/problemset/problem/977/A>
- Candidate function: `wrongSubtraction(number: Int, operations: Int): Int`
- Input contract: `2 <= number <= 1_000_000_000` and `1 <= operations <= 50`.
- Perform exactly `operations` state transitions. If the current value ends in a
  nonzero decimal digit, subtract one; otherwise divide it by ten using integer
  division. Return the final value.
- Mathematical reference: define `state(0) = number` and, for `i < operations`,
  `state(i + 1) = state(i) - 1` when `state(i) % 10 != 0`, and
  `state(i + 1) = state(i) / 10` otherwise. The result must equal
  `state(operations)`.
- Useful verification surface: exact loop counts, a data-dependent branch, remainder
  and integer division. The state stays nonnegative and never increases.

## Expectations for the solving and verification stages

Keep each implementation as a pure function with no console I/O. Preserve the
source bounds above as preconditions. The verification stage should express both
the functional result and loop progress; merely checking a handful of examples is
not sufficient. If one of the arithmetic operators is not supported by the current
SnaKt translation, document that limitation rather than weakening the contract.
