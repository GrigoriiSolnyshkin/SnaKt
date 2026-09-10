// FULL_JDK

// Candidate adapted from Codeforces 4A, "Watermelon":
// https://codeforces.com/problemset/problem/4/A
//
// Given a positive watermelon weight, decide whether it can be split into two
// positive even integer weights. This is a short paraphrase, not a reproduction
// of the original problem statement.

import org.jetbrains.kotlin.formver.plugin.postconditions
import org.jetbrains.kotlin.formver.plugin.preconditions

/**
 * Returns whether [weight] admits a split into two positive even weights.
 *
 * The exact characterization in the postcondition keeps the fixture independent
 * of console I/O and makes the intended competitive-programming result explicit.
 */
fun canSplitWatermelon(weight: Int): Boolean {
    preconditions {
        weight > 0
    }
    postconditions<Boolean> { result ->
        result == (weight > 2 && weight % 2 == 0)
    }

    return weight > 2 && weight % 2 == 0
}
