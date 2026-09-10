# Codeforces 734A — Anton and Danik

Source: [official Codeforces problem statement](https://codeforces.com/problemset/problem/734/A)

## Problem brief

Anton and Danik play exactly `n` chess games. The result string `games` has
length `n`; at every index, `A` means Anton won that game and `D` means Danik
won it. Drawn games do not occur.

Return:

- `"Anton"` when `games` contains more `A` characters than `D` characters;
- `"Danik"` when it contains more `D` characters than `A` characters;
- `"Friendship"` when the two counts are equal.

The original input constraints are:

- `1 <= n <= 100_000`;
- `games.length == n`;
- every character of `games` is either `A` or `D`.

For this repository, expose the algorithm as a pure function over `games`
rather than implementing contest input/output. Its preconditions should encode
the non-empty, maximum-length, and alphabet restrictions above.

## Examples

| `n` | `games` | result |
| ---: | :--- | :--- |
| 6 | `ADAAAA` | `Anton` |
| 7 | `DDDAADA` | `Danik` |
| 6 | `DADADA` | `Friendship` |

## Proof obligations

The implementation and its contracts should make the following properties
formally checkable:

1. **Index safety:** each access to `games[i]` is made with
   `0 <= i < games.length`.
2. **Processed-prefix accounting:** after processing the prefix `[0, i)`, the
   counters equal the numbers of `A` and `D` characters in that prefix.
3. **Counter bounds:** throughout the loop, both counters are non-negative and
   their sum is `i`. The alphabet precondition is needed for the sum equality.
4. **Progress and completion:** `i` remains in `0..games.length`, increases by
   one per iteration, and equals `games.length` when the loop exits.
5. **Classification:** the returned value is exactly the one selected by the
   comparison of the final full-string counts; no other string is returned.

Useful loop invariants therefore include the index bounds, non-negativity,
`antonWins + danikWins == i`, and quantified/counting facts connecting both
counters to `games[0 until i]`. If the available contract language cannot
express a cardinality directly, introduce a small verified prefix-count helper
or maintain an equivalent signed score (`A` adds one, `D` subtracts one) with a
prefix-fold invariant.

## Edge cases to cover

- the minimum length: `"A"` and `"D"`;
- an even-length exact tie such as `"AD"`;
- all wins belonging to one player;
- a winner decided only by the final game;
- the maximum permitted length (to establish linear traversal and safe integer
  bounds; counters never exceed `100_000`).
