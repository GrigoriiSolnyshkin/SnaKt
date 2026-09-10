# Codeforces 734A — Anton and Danik

## Source

- Original problem: https://codeforces.com/problemset/problem/734/A
- Contest: Codeforces Round 379 (Div. 2), problem A

This file paraphrases the original statement for use as additional formal-verification test data.

## Problem

A sequence records the results of games between Anton and Danik. Each character
is either:

- `A`, meaning Anton won that game; or
- `D`, meaning Danik won that game.

Return `"Anton"` if the sequence contains more Anton wins, `"Danik"` if it
contains more Danik wins, and `"Friendship"` if their win counts are equal.

## Constraints

- `1 <= games.length <= 100_000`
- Every character in `games` is either `A` or `D`.

These are preconditions of the intended function; malformed strings need not be
handled.

## Intended pure function

```kotlin
fun gameWinner(games: String): String
```

The function must not perform console input or output. Its result must be exactly
one of `"Anton"`, `"Danik"`, or `"Friendship"`.

## Examples

```text
gameWinner("ADAAAA") == "Anton"
gameWinner("DDDAADA") == "Danik"
gameWinner("DADADA") == "Friendship"
```

## Verification target

The implementation should preserve a loop invariant relating the processed
prefix to the current win counts, then show that the returned label agrees with
the comparison of the total counts.
