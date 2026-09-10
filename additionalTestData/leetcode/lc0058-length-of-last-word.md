# LC 58 — Length of Last Word

- Provenance: LeetCode problem 58, “Length of Last Word”
- Source: https://leetcode.com/problems/length-of-last-word/
- Statement status: paraphrased for this repository

## Required entry point

```kotlin
fun lengthOfLastWord(s: String): Int
```

## Input contract

- `s` contains only ASCII letters and the space character `' '`.
- `s` contains at least one letter.
- A word is a maximal non-empty contiguous run of non-space characters.

## Result contract

Let `end` be the largest valid index such that `s[end] != ' '`, and let
`start` be the smallest index in the same non-space run as `end`. The result
must equal `end - start + 1`.

An equivalent contract, often easier to encode using quantified conditions,
is that the result `r` is positive, the suffix after the last word contains
only spaces, the preceding `r` characters contain no spaces, and either that
word starts at index zero or the character immediately before it is a space.

## Representative cases

- `"Hello World" -> 5`
- `"   fly me   to   the moon  " -> 4`
- `"a" -> 1`
