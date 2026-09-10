package additionalTestData.leetcode

import org.jetbrains.kotlin.formver.plugin.*

/**
 * LeetCode 58: https://leetcode.com/problems/length-of-last-word/
 *
 * The input contains only ASCII letters and spaces and has at least one letter.
 * See lc0058-length-of-last-word.md for the collected specification.
 */
@AlwaysVerify
fun lengthOfLastWord(s: String): Int {
    preconditions {
        s.length > 0
        forAll<Int> { i ->
            (0 <= i && i < s.length) implies
                (s[i] == ' ' || ('A' <= s[i] && s[i] <= 'Z') ||
                    ('a' <= s[i] && s[i] <= 'z'))
        }
        exists<Int> { i -> 0 <= i && i < s.length && s[i] != ' ' }
    }
    postconditions<Int> { result ->
        1 <= result && result <= s.length
        exists<Int> { endIndex ->
            result - 1 <= endIndex && endIndex < s.length &&
                forAll<Int> { i ->
                    (endIndex < i && i < s.length) implies (s[i] == ' ')
                } &&
                forAll<Int> { i ->
                    (endIndex - result < i && i <= endIndex) implies (s[i] != ' ')
                } &&
                (endIndex - result < 0 || s[endIndex - result] == ' ')
        }
    }

    var end = s.length - 1
    while (s[end] == ' ') {
        loopInvariants {
            0 <= end && end < s.length
            forAll<Int> { i ->
                (end < i && i < s.length) implies (s[i] == ' ')
            }
            exists<Int> { i -> 0 <= i && i <= end && s[i] != ' ' }
        }
        end -= 1
    }

    var start = end
    while (start > 0 && s[start - 1] != ' ') {
        loopInvariants {
            0 <= start && start <= end && end < s.length
            s[end] != ' '
            forAll<Int> { i -> (end < i && i < s.length) implies (s[i] == ' ') }
            forAll<Int> { i -> (start <= i && i <= end) implies (s[i] != ' ') }
        }
        start -= 1
    }
    return end - start + 1
}
