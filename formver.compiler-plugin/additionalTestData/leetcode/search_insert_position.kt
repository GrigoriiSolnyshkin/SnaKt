// FULL_JDK

import org.jetbrains.kotlin.formver.plugin.AlwaysVerify
import org.jetbrains.kotlin.formver.plugin.forAll
import org.jetbrains.kotlin.formver.plugin.implies
import org.jetbrains.kotlin.formver.plugin.loopInvariants
import org.jetbrains.kotlin.formver.plugin.postconditions
import org.jetbrains.kotlin.formver.plugin.preconditions

// Adapted from LeetCode 35, "Search Insert Position":
// https://leetcode.com/problems/search-insert-position/
//
// SnaKt currently rejects List.get calls in quantified specifications, so this
// fixture uses a strictly increasing String as the supported finite sequence.
// The algorithm and insertion-position contract are otherwise the same.
@AlwaysVerify
fun searchInsert(chars: String, target: Char): Int {
    preconditions {
        forAll<Int> { i ->
            (0 <= i && i + 1 < chars.length) implies (chars[i] < chars[i + 1])
        }
    }
    postconditions<Int> { result ->
        0 <= result && result <= chars.length
        forAll<Int> { i ->
            (0 <= i && i < result) implies (chars[i] < target)
        }
        (result != chars.length) implies (target <= chars[result])
    }

    var insertionIndex = 0
    while (insertionIndex < chars.length && chars[insertionIndex] < target) {
        loopInvariants {
            0 <= insertionIndex && insertionIndex <= chars.length
            forAll<Int> { i ->
                (0 <= i && i < insertionIndex) implies (chars[i] < target)
            }
        }
        insertionIndex++
    }
    return insertionIndex
}
