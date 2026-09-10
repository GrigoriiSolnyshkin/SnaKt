# LeetCode 9: Palindrome Number

## Included artifacts

This directory contains the collected problem specification and the isolated
SnaKt implementation in `PalindromeNumber.kt`. It is not registered in the
repository's normal `testData` suite.

## Source

- Primary problem page: https://leetcode.com/problems/palindrome-number/
- Problem number: 9
- Title: Palindrome Number

The source page was selected as the canonical statement. Network access to
LeetCode was unavailable during collection, so the specification below records
the stable, well-known problem contract rather than claiming a fresh copy of the
page contents.

## Task

Define a function with the following intended interface:

```kotlin
fun isPalindrome(x: Int): Boolean
```

The function returns `true` exactly when the usual base-10 representation of
`x` reads identically from left to right and right to left.

Additional semantic details:

- A negative integer is not a palindrome: its leading minus sign has no
  matching trailing sign.
- No leading zeroes are added to the representation. Consequently, a positive
  number ending in zero is not a palindrome unless the number itself is zero.
- Zero and every positive one-digit integer are palindromes.
- The intended solution must not convert the integer to a string.

## Input constraint

The input is any signed 32-bit integer:

```text
-2^31 <= x <= 2^31 - 1
```

In Kotlin this is exactly the range of `Int`, so the implementation needs no
separate input precondition.

## Mathematical behavior

For `x >= 0`, let `digits(x)` be the unique finite sequence of decimal digits
with no leading zeroes (and let `digits(0) = [0]`). Then:

```text
isPalindrome(x) =
    false                                      if x < 0
    (digits(x) = reverse(digits(x)))           otherwise
```

An equivalent arithmetic characterization, useful for verification, compares
only half of the digits. Repeatedly remove the last digit from a remaining
prefix and append it to a reversed suffix until the reversed suffix is at least
the remaining prefix. The original number is a palindrome exactly when either:

```text
remainingPrefix == reversedSuffix
```

for an even number of digits, or:

```text
remainingPrefix == reversedSuffix / 10
```

for an odd number of digits (where the middle digit is discarded).

## Examples

```text
Input:  x = 121
Output: true
Reason: 121 reads the same in both directions.

Input:  x = -121
Output: false
Reason: reversing the written form gives 121-, not -121.

Input:  x = 10
Output: false
Reason: reversing the digits gives 01; leading zeroes are not part of the
        ordinary representation.

Input:  x = 0
Output: true
```

## Suggested solution and proof shape

1. Return `false` when `x < 0` or when `x` ends in zero but is not zero.
2. Set `remainingPrefix = x` and `reversedSuffix = 0`.
3. While `remainingPrefix > reversedSuffix`, move one decimal digit:
   `reversedSuffix = reversedSuffix * 10 + remainingPrefix % 10`, then
   `remainingPrefix /= 10`.
4. Return whether either arithmetic equality in the previous section holds.

This version is a good fit for SnaKt because it uses only local `Int` values,
division, remainder, comparisons, and a terminating loop; it needs no arrays,
lists, strings, recursion, or heap reasoning.

A formal solution should establish:

- the loop terminates because each iteration divides a positive
  `remainingPrefix` by 10;
- the moved digits in `reversedSuffix` are the reverse of the digits removed
  from the original value;
- the initial rejection of nonzero values ending in zero is sound;
- `reversedSuffix * 10 + digit` cannot overflow. Since only about half the
  digits are reversed, `reversedSuffix` remains small enough for `Int`; this
  bound should be made explicit in the loop invariant rather than assumed;
- at loop exit, the two equality cases correspond precisely to even- and
  odd-length decimal representations.

The implementation agent may introduce a ghost/model value for the original
input if needed, but should preserve the requested public behavior for every
Kotlin `Int`.

## Included solution and proof

`PalindromeNumber.kt` uses the equivalent bounded digit-comparison approach.
For each decimal length from one through ten it extracts mirrored digits using
division and remainder and requires every mirrored pair to be equal. This is
exact because every non-negative `Int` has at most ten decimal digits; the
negative branch returns false. The public postcondition equates its result to
this pure arithmetic model.

This formulation uses neither strings nor reversal multiplication, so all
divisors are positive constants, no arithmetic operation can overflow, and
there is no loop or recursion requiring an invariant or termination measure.
The ten-digit branch is reachable only for positive values at least one
billion (negative values were handled first), and compares all five pairs.
