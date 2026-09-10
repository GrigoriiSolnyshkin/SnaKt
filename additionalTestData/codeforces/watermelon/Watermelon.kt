// FULL_JDK

package additional.codeforces.watermelon

import org.jetbrains.kotlin.formver.plugin.AlwaysVerify
import org.jetbrains.kotlin.formver.plugin.postconditions
import org.jetbrains.kotlin.formver.plugin.preconditions

/** The verifiable core of Codeforces 4A. */
@AlwaysVerify
fun canSplitWatermelon(weight: Int): Boolean {
    preconditions {
        1 <= weight
        weight <= 100
    }
    postconditions<Boolean> { result ->
        result == (weight > 2 && weight % 2 == 0)
    }

    return weight > 2 && weight % 2 == 0
}
