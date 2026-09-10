# Subtract the Product and Sum of Digits of an Integer

- Platform: LeetCode
- Problem: 1281
- Source: https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
- Difficulty: Easy

## Paraphrased statement

Given a positive base-10 integer `n`, examine all of its decimal digits. Return the
product of those digits minus their sum.

The intended Kotlin entry point is:

```kotlin
fun subtractProductAndSum(n: Int): Int
```

## Constraints

- `1 <= n <= 100_000`
- `n` is represented in ordinary base 10 with no leading zeroes.
- The result fits in a Kotlin `Int` under these constraints. In particular, the
  greatest possible digit product is small enough that intermediate products do
  not overflow.

## Function contract

Precondition:

- `n` is between `1` and `100_000`, inclusive.

Postcondition:

- If `digits(n)` is the finite sequence of decimal digits of `n`, then the result
  equals `product(digits(n)) - sum(digits(n))`.
- Every decimal digit participates exactly once in both aggregates.
- The input value is not modified.

Here `digits(n)` is ordered from least significant to most significant; the order
does not affect either aggregate. For the boundary value `100_000`, its digits are
`[0, 0, 0, 0, 0, 1]`.

## Examples

1. `n = 234`: product `2 * 3 * 4 = 24`, sum `2 + 3 + 4 = 9`, result `15`.
2. `n = 4421`: product `4 * 4 * 2 * 1 = 32`, sum `4 + 4 + 2 + 1 = 11`, result `21`.
3. `n = 10`: product `1 * 0 = 0`, sum `1 + 0 = 1`, result `-1`.

## Correctness properties to verify

A direct solution may repeatedly extract `remaining % 10` and then replace
`remaining` with `remaining / 10`. Its proof should establish:

1. **Progress and termination:** `remaining` is non-negative and strictly
   decreases on every iteration in which it is positive.
2. **Decimal decomposition:** at every loop boundary, the original `n` consists
   of the unprocessed prefix represented by `remaining` followed by exactly the
   already-processed suffix.
3. **Sum invariant:** the running sum equals the sum of every processed digit and
   no unprocessed digit.
4. **Product invariant:** the running product equals the product of every
   processed digit and no unprocessed digit. It must initially be `1`, not `0`.
5. **Zero-digit handling:** extracting a zero leaves the sum unchanged and makes
   the running product zero; subsequent iterations preserve that zero product.
6. **Completion:** when `remaining == 0`, all and only the digits of the original
   input have been processed, so subtracting the two accumulators satisfies the
   postcondition.
7. **Arithmetic safety:** use the stated input bound to show that the accumulator
   updates and final subtraction remain within `Int` range.

The formalized solution should use only integer variables, `%`, `/`, addition,
subtraction, multiplication, comparisons, and a `while` loop, matching the
verification plugin's compact arithmetic-test style.
