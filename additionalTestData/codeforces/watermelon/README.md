# Codeforces 4A — Watermelon

## Source

- Official problem page: https://codeforces.com/problemset/problem/4/A
- Problem: 4A, **Watermelon**

This document restates the mathematical requirements needed to implement and
verify the solution. Consult the official page for the canonical wording.

## Problem

Given the integer weight `w` of a watermelon, decide whether it can be split
into two parts whose weights are both:

1. positive integers, and
2. even.

The two part weights must add up to `w`. Print `YES` when such a split exists
and `NO` otherwise. Codeforces accepts any capitalization of those words.

## Input and constraints

The input consists of one integer:

```text
w
```

with `1 <= w <= 100`.

## Mathematical behavior

Let `canSplit(w)` denote the answer. Its exact specification is:

```text
canSplit(w) == (w > 2 && w % 2 == 0)
```

Equivalently, `canSplit(w)` is true exactly when there exist positive even
integers `a` and `b` such that `a + b == w`.

Why this characterization is complete:

- If such `a` and `b` exist, their sum is even and is at least `2 + 2 = 4`.
  Therefore `w` is even and greater than `2`.
- If `w` is even and greater than `2`, choose `a = 2` and `b = w - 2`.
  Both values are positive and even, and their sum is `w`.

The lower-bound check is essential: `w = 2` is even but can only be written as
`1 + 1` using positive parts, and those parts are odd.

## Examples

### Example 1

```text
Input
8

Output
YES
```

One valid split is `2 + 6`.

### Example 2

```text
Input
5

Output
NO
```

An odd number cannot be the sum of two even integers.

## Suggested SnaKt solution shape

Keep the verifiable core separate from console input/output:

```text
fun canSplitWatermelon(w: Int): Boolean
```

Use a precondition `1 <= w && w <= 100` and a Boolean postcondition requiring
the result to equal `w > 2 && w % 2 == 0`. The implementation can return that
expression directly. A thin, unverified wrapper may parse the input and map
the Boolean result to `YES` or `NO` if an executable submission is desired.

No arrays, collections, loops, mutation, recursion, or overflow-sensitive
arithmetic are required.

## Included solution and proof

`Watermelon.kt` implements the expression above directly. Its precondition is
the problem's complete input range and its postcondition is the biconditional
between the returned Boolean and `weight > 2 && weight % 2 == 0`. Because the
body returns that same expression, the postcondition follows by substitution.
There is no loop or recursion, hence no termination obligation.
