# LC 35 — Search Insert Position

- Provenance: LeetCode problem 35, “Search Insert Position”
- Source: https://leetcode.com/problems/search-insert-position/
- Statement status: paraphrased for this repository

## Required entry point

```kotlin
fun searchInsert(nums: List<Int>, target: Int): Int
```

## Input contract

- `nums` is sorted in strictly increasing order.
- `nums` may be empty.
- To keep arithmetic verification independent of Kotlin overflow details, the
  implementation must compute a midpoint as `lo + (hi - lo) / 2`, not
  `(lo + hi) / 2`.

## Result contract

Let `r` be the returned value.

- `0 <= r && r <= nums.size`.
- Every index `i` with `0 <= i && i < r` satisfies `nums[i] < target`.
- Every index `i` with `r <= i && i < nums.size` satisfies
  `target <= nums[i]`.

Thus `r` is the unique lower-bound position: it is the existing position of
`target` when present, otherwise the position where inserting `target`
preserves sorted order.

## Representative cases

- `([], 4) -> 0`
- `([1, 3, 5, 6], 5) -> 2`
- `([1, 3, 5, 6], 2) -> 1`
- `([1, 3, 5, 6], 7) -> 4`
