// FULL_JDK

import org.jetbrains.kotlin.formver.plugin.*

@AlwaysVerify
fun canSplitEvenly(weight: Int): Boolean {
    preconditions {
        1 <= weight
        weight <= 100
    }
    postconditions<Boolean> { result ->
        result == (weight > 2 && weight % 2 == 0)
        result implies (weight - 2 > 0)
        result implies ((weight - 2) % 2 == 0)
        result implies (2 + (weight - 2) == weight)
    }

    return weight > 2 && weight % 2 == 0
}
