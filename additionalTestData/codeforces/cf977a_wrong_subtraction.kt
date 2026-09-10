package additionalTestData.codeforces

import org.jetbrains.kotlin.formver.plugin.AlwaysVerify
import org.jetbrains.kotlin.formver.plugin.loopInvariants
import org.jetbrains.kotlin.formver.plugin.postconditions
import org.jetbrains.kotlin.formver.plugin.preconditions

/**
 * Codeforces 977A: https://codeforces.com/problemset/problem/977/A
 *
 * Contract: [number] is in 2..1_000_000_000 and [operations] is in 1..50.
 * Repeat exactly [operations] times: subtract one when the current value's last
 * decimal digit is nonzero; otherwise divide the value by ten. Return the final
 * value.
 */
@AlwaysVerify
fun wrongSubtraction(number: Int, operations: Int): Int {
    preconditions {
        2 <= number && number <= 1_000_000_000
        1 <= operations && operations <= 50
    }
    postconditions<Int> { result -> 0 <= result && result <= number }

    var current = number
    var completed = 0
    while (completed < operations) {
        loopInvariants {
            0 <= completed && completed <= operations
            0 <= current && current <= number
        }
        current = if (current % 10 != 0) current - 1 else current / 10
        completed += 1
    }
    return current
}
