# Subtract the Product and Sum of Digits of an Integer

## Source

- LeetCode problem 1281: https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
- The statement below is paraphrased for this repository rather than copied from
  the source.

## Problem

Given a positive decimal integer `n`, calculate the product of all of its decimal
digits and the sum of all of its decimal digits. Return the product minus the sum.

## Constraints

- `1 <= n <= 100_000`

## Examples

### Example 1

- Input: `n = 234`
- Output: `15`
- Reason: the digit product is `2 * 3 * 4 = 24`, the digit sum is
  `2 + 3 + 4 = 9`, and `24 - 9 = 15`.

### Example 2

- Input: `n = 4421`
- Output: `21`
- Reason: the digit product is `4 * 4 * 2 * 1 = 32`, the digit sum is
  `4 + 4 + 2 + 1 = 11`, and `32 - 11 = 21`.

## Intended Kotlin API

Implement this pure function without collection-building helpers:

```kotlin
fun subtractProductAndSum(n: Int): Int
```

The function must not mutate external state. Integer division and remainder by
`10` are sufficient to inspect the digits.
