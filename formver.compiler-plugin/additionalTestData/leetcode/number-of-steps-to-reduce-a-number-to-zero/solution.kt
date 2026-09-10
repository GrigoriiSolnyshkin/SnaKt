import org.jetbrains.kotlin.formver.plugin.AlwaysVerify
import org.jetbrains.kotlin.formver.plugin.Pure
import org.jetbrains.kotlin.formver.plugin.implies
import org.jetbrains.kotlin.formver.plugin.loopInvariants
import org.jetbrains.kotlin.formver.plugin.postconditions
import org.jetbrains.kotlin.formver.plugin.preconditions

@Pure
fun reductionSteps(value: Int): Int {
    preconditions {
        value >= 0
    }
    postconditions<Int> { result ->
        result >= 0
        (value == 0) implies (result == 0)
        (value > 0 && value % 2 == 0) implies (result == reductionSteps(value / 2) + 1)
        (value > 0 && value % 2 != 0) implies (result == reductionSteps(value - 1) + 1)
    }

    return if (value == 0) {
        0
    } else if (value % 2 == 0) {
        reductionSteps(value / 2) + 1
    } else {
        reductionSteps(value - 1) + 1
    }
}

@AlwaysVerify
fun numberOfSteps(num: Int): Int {
    preconditions {
        0 <= num && num <= 1_000_000
    }
    postconditions<Int> { result ->
        result == reductionSteps(num)
        result >= 0
    }

    var value = num
    var steps = 0
    while (value > 0) {
        loopInvariants {
            0 <= value && value <= num
            steps >= 0
            steps + reductionSteps(value) == reductionSteps(num)
        }
        if (value % 2 == 0) {
            value /= 2
        } else {
            value -= 1
        }
        steps += 1
    }
    return steps
}
