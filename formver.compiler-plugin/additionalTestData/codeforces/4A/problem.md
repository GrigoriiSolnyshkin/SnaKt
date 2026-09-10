# Codeforces 4A — Watermelon

## Source

- Original problem: https://codeforces.com/problemset/problem/4/A
- Contest: Codeforces Beta Round 4, problem A

This file paraphrases the original statement for use as additional formal-verification test data.

## Problem

Given a watermelon whose weight is `weight` kilograms, decide whether it can be
split into two parts such that:

- both parts have positive even integer weights; and
- the two parts together have weight `weight`.

The parts do not have to weigh the same amount.

## Constraints

- `1 <= weight <= 100`
- `weight` is an integer.

## Intended pure function

```kotlin
fun canSplitEvenly(weight: Int): Boolean
```

The function returns `true` exactly when such a split exists. It must not perform
console input or output.

## Examples

```text
canSplitEvenly(8) == true
```

One valid split is `2 + 6`.

```text
canSplitEvenly(5) == false
```

No sum of two positive even integers is odd.

## Verification target

The implementation should establish that every accepted weight has two positive
even summands, and that every rejected weight has no such pair of summands.
