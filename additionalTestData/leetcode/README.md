# LeetCode-derived verification candidates

This directory is an isolated staging area. It is deliberately outside
`formver.compiler-plugin/testData`, so its contents are not discovered by the
normal generated test suite.

The problem statements below are short paraphrases, not copies of LeetCode's
statements. Each file records the source identifier and URL, the Kotlin entry
point to implement, and a mathematical contract suitable for formal
verification. The eventual implementation should use SnaKt contracts and
`@AlwaysVerify`, following the examples in
`formver.compiler-plugin/testData/diagnostics/expensive_verification/algorithms`.

## Selected set

| File | Source | Main verification feature |
| --- | --- | --- |
| `lc0035-search-insert-position.md` | LeetCode 35 | sorted-list reasoning, lower-bound loop invariant |
| `lc0058-length-of-last-word.md` | LeetCode 58 | string indexing, backward loop, boundary cases |
| `lc0121-best-time-to-buy-and-sell-stock.md` | LeetCode 121 | running minimum/maximum, quantified optimality |

The adjacent underscore-named Kotlin files are pure-function solutions with
SnaKt contracts and loop invariants. No test golden is included at this staging
stage.
