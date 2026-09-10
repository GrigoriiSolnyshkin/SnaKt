# LeetCode 509: Fibonacci Number

Source: [LeetCode — Fibonacci Number](https://leetcode.com/problems/fibonacci-number/)

## Problem brief

Given an integer `n`, return the `n`th Fibonacci number. The sequence is defined
by:

- `F(0) = 0`
- `F(1) = 1`
- `F(n) = F(n - 1) + F(n - 2)` for `n > 1`

The input constraint is `0 <= n <= 30`.

This additional test should expose a single Kotlin function over `Int` values;
it should not perform console or file I/O. An iterative implementation is
preferred so that the verifier is exercised on a bounded arithmetic loop rather
than on recursive-call termination.

## Examples

- Input: `n = 2`; output: `1`. (`F(2) = F(1) + F(0) = 1`.)
- Input: `n = 3`; output: `2`. (`F(3) = F(2) + F(1) = 2`.)
- Input: `n = 4`; output: `3`. (`F(4) = F(3) + F(2) = 3`.)

## Proof obligations

The eventual solution and its contracts should establish all of the following:

1. The function's precondition captures the full LeetCode domain,
   `0 <= n <= 30`.
2. The returned value equals the sequence value specified by the two base cases
   and recurrence above, rather than merely matching the listed examples.
3. Each loop iteration preserves the relationship between its consecutive
   accumulator values and the corresponding consecutive Fibonacci numbers.
4. The loop index stays within its intended bounds, advances on every
   iteration, and terminates at the index needed for `F(n)`.
5. All additions fit in Kotlin's signed 32-bit `Int`. In particular, the input
   bound implies the result is at most `F(30) = 832040`; accumulator bounds
   should make the absence of intermediate overflow explicit.
6. The postcondition handles both base cases directly and does not rely on the
   loop executing at least once.

## Edge cases to cover

- `n = 0`, whose result is `0` and for which an iterative loop may be empty.
- `n = 1`, whose result is `1` and may follow a separate base-case path.
- `n = 2`, the first value computed from the recurrence.
- `n = 30`, the largest permitted input and the important arithmetic-boundary
  case.

Keep any implementation, verifier annotations, and generated golden files for
this problem inside this directory. Do not add it to the main `testData` tree.
