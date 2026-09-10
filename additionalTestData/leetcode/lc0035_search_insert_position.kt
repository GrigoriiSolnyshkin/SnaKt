package additionalTestData.leetcode

import org.jetbrains.kotlin.formver.plugin.*

/**
 * LeetCode 35: https://leetcode.com/problems/search-insert-position/
 *
 * The input is strictly increasing. The result is its lower-bound position for
 * [target]. See lc0035-search-insert-position.md for the collected specification.
 */
@AlwaysVerify
fun searchInsert(nums: List<Int>, target: Int): Int {
    preconditions {
        forAll<Int> { i ->
            forAll<Int> { j ->
                (0 <= i && i < j && j < nums.size) implies (nums[i] < nums[j])
            }
        }
    }
    postconditions<Int> { result ->
        0 <= result && result <= nums.size
        forAll<Int> { i -> (0 <= i && i < result) implies (nums[i] < target) }
        forAll<Int> { i ->
            (result <= i && i < nums.size) implies (target <= nums[i])
        }
    }

    var lo = 0
    var hi = nums.size
    while (lo < hi) {
        loopInvariants {
            0 <= lo && lo <= hi && hi <= nums.size
            forAll<Int> { i -> (0 <= i && i < lo) implies (nums[i] < target) }
            forAll<Int> { i ->
                (hi <= i && i < nums.size) implies (target <= nums[i])
            }
        }
        val mid = lo + (hi - lo) / 2
        if (nums[mid] < target) lo = mid + 1 else hi = mid
    }
    return lo
}
