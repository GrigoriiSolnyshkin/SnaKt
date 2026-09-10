import org.jetbrains.kotlin.formver.plugin.AlwaysVerify
import org.jetbrains.kotlin.formver.plugin.Pure
import org.jetbrains.kotlin.formver.plugin.implies
import org.jetbrains.kotlin.formver.plugin.loopInvariants
import org.jetbrains.kotlin.formver.plugin.postconditions
import org.jetbrains.kotlin.formver.plugin.preconditions

@Pure
fun decimalDigitSum(value: Int): Int {
    preconditions {
        value >= 0
    }
    postconditions<Int> { result ->
        result >= 0
        (value == 0) implies (result == 0)
        (value > 0) implies (result == value % 10 + decimalDigitSum(value / 10))
    }

    return if (value == 0) 0 else value % 10 + decimalDigitSum(value / 10)
}

@Pure
fun decimalDigitProduct(value: Int): Int {
    preconditions {
        value >= 0
    }
    postconditions<Int> { result ->
        result >= 0
        (value == 0) implies (result == 1)
        (value > 0) implies (result == value % 10 * decimalDigitProduct(value / 10))
    }

    return if (value == 0) 1 else value % 10 * decimalDigitProduct(value / 10)
}

@AlwaysVerify
fun subtractProductAndSum(n: Int): Int {
    preconditions {
        1 <= n && n <= 100_000
    }
    postconditions<Int> { result ->
        result == decimalDigitProduct(n) - decimalDigitSum(n)
    }

    var value = n
    var product = 1
    var sum = 0
    while (value > 0) {
        loopInvariants {
            0 <= value && value <= n
            product >= 0 && sum >= 0
            product * decimalDigitProduct(value) == decimalDigitProduct(n)
            sum + decimalDigitSum(value) == decimalDigitSum(n)
        }
        val digit = value % 10
        product *= digit
        sum += digit
        value /= 10
    }
    return product - sum
}
