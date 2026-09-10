// FULL_JDK

package additional.leetcode.palindrome_number

import org.jetbrains.kotlin.formver.plugin.AlwaysVerify
import org.jetbrains.kotlin.formver.plugin.Pure
import org.jetbrains.kotlin.formver.plugin.postconditions

/**
 * Exact arithmetic model of equality between a non-negative Int's decimal
 * digits and their reversal. Each branch handles one possible digit count.
 */
@Pure
fun decimalDigitsReadSame(x: Int): Boolean =
    when {
        x < 0 -> false
        x < 10 -> true
        x < 100 -> x / 10 == x % 10
        x < 1_000 -> x / 100 == x % 10
        x < 10_000 ->
            x / 1_000 == x % 10 &&
                x / 100 % 10 == x / 10 % 10
        x < 100_000 ->
            x / 10_000 == x % 10 &&
                x / 1_000 % 10 == x / 10 % 10
        x < 1_000_000 ->
            x / 100_000 == x % 10 &&
                x / 10_000 % 10 == x / 10 % 10 &&
                x / 1_000 % 10 == x / 100 % 10
        x < 10_000_000 ->
            x / 1_000_000 == x % 10 &&
                x / 100_000 % 10 == x / 10 % 10 &&
                x / 10_000 % 10 == x / 100 % 10
        x < 100_000_000 ->
            x / 10_000_000 == x % 10 &&
                x / 1_000_000 % 10 == x / 10 % 10 &&
                x / 100_000 % 10 == x / 100 % 10 &&
                x / 10_000 % 10 == x / 1_000 % 10
        x < 1_000_000_000 ->
            x / 100_000_000 == x % 10 &&
                x / 10_000_000 % 10 == x / 10 % 10 &&
                x / 1_000_000 % 10 == x / 100 % 10 &&
                x / 100_000 % 10 == x / 1_000 % 10
        else ->
            x / 1_000_000_000 == x % 10 &&
                x / 100_000_000 % 10 == x / 10 % 10 &&
                x / 10_000_000 % 10 == x / 100 % 10 &&
                x / 1_000_000 % 10 == x / 1_000 % 10 &&
                x / 100_000 % 10 == x / 10_000 % 10
    }

/** LeetCode 9 without string conversion or overflow-sensitive reversal. */
@AlwaysVerify
fun isPalindrome(x: Int): Boolean {
    postconditions<Boolean> { result ->
        result == decimalDigitsReadSame(x)
    }

    return decimalDigitsReadSame(x)
}
