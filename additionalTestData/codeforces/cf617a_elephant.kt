package additionalTestData.codeforces

import org.jetbrains.kotlin.formver.plugin.AlwaysVerify
import org.jetbrains.kotlin.formver.plugin.postconditions
import org.jetbrains.kotlin.formver.plugin.preconditions

/**
 * Codeforces 617A: https://codeforces.com/problemset/problem/617/A
 *
 * Contract: [distance] is in 1..1_000_000. Return the least positive number of
 * moves needed to cover [distance] exactly when every move has an integer length
 * in 1..5.
 */
@AlwaysVerify
fun minimumSteps(distance: Int): Int {
    preconditions { 1 <= distance && distance <= 1_000_000 }
    postconditions<Int> { steps ->
        steps > 0
        distance <= 5 * steps
        5 * (steps - 1) < distance
    }
    return (distance + 4) / 5
}
