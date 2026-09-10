# Number of Steps to Reduce a Number to Zero

## Source

- LeetCode problem 1342: https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
- The statement below is paraphrased for this repository rather than copied from
  the source.

## Problem

Start with a non-negative integer `num`. Repeatedly apply exactly one operation:

- if the current value is even, divide it by `2`;
- otherwise, subtract `1`.

Return how many operations are required to reach `0`.

## Constraints

- `0 <= num <= 1_000_000`

## Examples

### Example 1

- Input: `num = 14`
- Output: `6`
- Trace: `14 -> 7 -> 6 -> 3 -> 2 -> 1 -> 0`.

### Example 2

- Input: `num = 8`
- Output: `4`
- Trace: `8 -> 4 -> 2 -> 1 -> 0`.

### Example 3

- Input: `num = 123`
- Output: `12`

## Intended Kotlin API

Implement this pure function using basic integer arithmetic:

```kotlin
fun numberOfSteps(num: Int): Int
```

The function must not mutate external state. `% 2`, division by `2`, and a loop
are sufficient; no collection APIs are needed.
