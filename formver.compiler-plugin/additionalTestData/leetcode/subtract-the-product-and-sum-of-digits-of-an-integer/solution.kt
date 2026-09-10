// FULL_JDK

import org.jetbrains.kotlin.formver.plugin.*

@Pure
fun decimalDigitSum(value: Int): Int {
    preconditions { 0 <= value; value <= 100_000 }
    postconditions<Int> { result ->
        result == value % 10 +
            (value / 10) % 10 +
            (value / 100) % 10 +
            (value / 1_000) % 10 +
            (value / 10_000) % 10 +
            (value / 100_000) % 10
    }

    return value % 10 +
        (value / 10) % 10 +
        (value / 100) % 10 +
        (value / 1_000) % 10 +
        (value / 10_000) % 10 +
        (value / 100_000) % 10
}

@Pure
fun decimalDigitProduct(value: Int): Int {
    preconditions { 0 <= value; value <= 100_000 }
    postconditions<Int> { result ->
        (value == 0) implies (result == 1)
        (0 < value && value < 10) implies (result == value)
        (10 <= value && value < 100) implies
            (result == (value % 10) * ((value / 10) % 10))
        (100 <= value && value < 1_000) implies
            (result == (value % 10) * ((value / 10) % 10) * ((value / 100) % 10))
        (1_000 <= value && value < 10_000) implies
            (result == (value % 10) * ((value / 10) % 10) * ((value / 100) % 10) * ((value / 1_000) % 10))
        (10_000 <= value && value < 100_000) implies
            (result == (value % 10) * ((value / 10) % 10) * ((value / 100) % 10) * ((value / 1_000) % 10) * ((value / 10_000) % 10))
        (value == 100_000) implies (result == 0)
    }

    return if (value == 0) 1
    else if (value < 10) value
    else if (value < 100) (value % 10) * ((value / 10) % 10)
    else if (value < 1_000) (value % 10) * ((value / 10) % 10) * ((value / 100) % 10)
    else if (value < 10_000) (value % 10) * ((value / 10) % 10) * ((value / 100) % 10) * ((value / 1_000) % 10)
    else if (value < 100_000) (value % 10) * ((value / 10) % 10) * ((value / 100) % 10) * ((value / 1_000) % 10) * ((value / 10_000) % 10)
    else 0
}

@AlwaysVerify
fun subtractProductAndSum(n: Int): Int {
    preconditions {
        1 <= n
        n <= 100_000
    }
    postconditions<Int> { result ->
        result == decimalDigitProduct(n) - decimalDigitSum(n)
    }

    return decimalDigitProduct(n) - decimalDigitSum(n)
}
