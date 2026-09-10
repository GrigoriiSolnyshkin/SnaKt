# Codeforces 4A — Watermelon

## Metadata

- Source: [Codeforces Problem 4A](https://codeforces.com/problemset/problem/4/A)
- Difficulty: 800
- Tags: brute force, math
- Intended verification target: a pure Kotlin function over `Int` returning `Boolean`

## Paraphrased statement

Given the integer weight of a watermelon, decide whether it can be split into
exactly two pieces such that both pieces have positive even integer weights.
The pieces do not need to have equal weights.

## Constraints

- `1 <= weight <= 100`
- `weight` is an integer.

## Input and output intent

The original problem reads one integer `weight` and prints `YES` if such a
split exists, otherwise `NO`.

For the verification test, keep parsing and printing out of scope. Implement a
function with the semantic shape `canSplitEvenly(weight: Int): Boolean`, where
`true` corresponds to `YES` and `false` corresponds to `NO`.

## Examples

### Example 1

Input: `8`

Output: `YES`

Explanation: `8` can be split as `2 + 6`, and both summands are positive and
even.

### Example 2

Input: `2`

Output: `NO`

Explanation: the only split into positive integers is `1 + 1`; neither piece
is even.

## Correctness properties

The later solution and its formal contract must establish all of the following:

1. **Domain precondition:** `1 <= weight && weight <= 100`.
2. **Exact result characterization:** the returned value is `true` if and only
   if `weight > 2 && weight % 2 == 0`.
3. **Soundness of `true`:** when the function returns `true`, there are positive
   even integers `left` and `right` with `left + right == weight`. A constructive
   witness is `left = 2` and `right = weight - 2`.
4. **Completeness of `false`:** when `weight <= 2`, two positive even parts are
   impossible because their sum is at least `4`; when `weight` is odd, two even
   parts are impossible because their sum would be even.
5. **Boundary behavior:** weights `1`, `2`, and `3` return `false`; weight `4`
   returns `true`; weight `100` returns `true`.

The primary postcondition should use property 2 directly. Properties 3 and 4
explain why that executable Boolean condition is equivalent to the original
existential statement, without requiring quantified arithmetic in the test.
