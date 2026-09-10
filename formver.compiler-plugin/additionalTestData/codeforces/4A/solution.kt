import org.jetbrains.kotlin.formver.plugin.AlwaysVerify
import org.jetbrains.kotlin.formver.plugin.implies
import org.jetbrains.kotlin.formver.plugin.postconditions
import org.jetbrains.kotlin.formver.plugin.preconditions

@AlwaysVerify
fun canSplitEvenly(weight: Int): Boolean {
    preconditions {
        1 <= weight && weight <= 100
    }
    postconditions<Boolean> { result ->
        result == (weight > 2 && weight % 2 == 0)
        result implies (2 > 0 && weight - 2 > 0 && 2 % 2 == 0 && (weight - 2) % 2 == 0)
        (!result) implies (weight <= 2 || weight % 2 != 0)
    }

    return weight > 2 && weight % 2 == 0
}
