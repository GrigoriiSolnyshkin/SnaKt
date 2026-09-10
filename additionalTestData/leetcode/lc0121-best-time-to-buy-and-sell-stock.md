# LC 121 — Best Time to Buy and Sell Stock

- Provenance: LeetCode problem 121, “Best Time to Buy and Sell Stock”
- Source: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
- Statement status: paraphrased for this repository

## Required entry point

```kotlin
fun maxProfit(prices: List<Int>): Int
```

## Input contract

- `prices` is non-empty.
- Every price is in `0..1_000_000_000`. This explicit bound ensures every
  subtraction performed by a conforming solution is representable as `Int`.
- A transaction buys on one day and sells on a strictly later day. Performing
  no transaction is allowed.

## Result contract

For the returned value `p`:

- `p >= 0`.
- For every pair of indices `buy`, `sell` satisfying
  `0 <= buy && buy < sell && sell < prices.size`,
  `prices[sell] - prices[buy] <= p`.
- Either `p == 0`, or there exist indices `buy`, `sell` satisfying the same
  ordering constraints with `p == prices[sell] - prices[buy]`.

Together these conditions say that `p` is exactly the greatest obtainable
single-transaction profit, with zero chosen if all price differences are
negative.

## Representative cases

- `[7, 1, 5, 3, 6, 4] -> 5`
- `[7, 6, 4, 3, 1] -> 0`
- `[4] -> 0`
