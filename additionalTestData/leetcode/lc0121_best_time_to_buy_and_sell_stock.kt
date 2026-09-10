package additionalTestData.leetcode

import org.jetbrains.kotlin.formver.plugin.*

/**
 * LeetCode 121: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *
 * Prices are nonnegative and at most 1,000,000,000. Return the maximum profit
 * of one buy followed by one sale, or zero. See the adjacent markdown spec.
 */
@AlwaysVerify
fun maxProfit(prices: List<Int>): Int {
    preconditions {
        prices.isNotEmpty()
        forAll<Int> { i ->
            (0 <= i && i < prices.size) implies
                (0 <= prices[i] && prices[i] <= 1_000_000_000)
        }
    }
    postconditions<Int> { profit ->
        profit >= 0
        forAll<Int> { buy ->
            forAll<Int> { sell ->
                (0 <= buy && buy < sell && sell < prices.size) implies
                    (prices[sell] - prices[buy] <= profit)
            }
        }
        profit == 0 || exists<Int> { buy ->
            exists<Int> { sell ->
                0 <= buy && buy < sell && sell < prices.size &&
                    profit == prices[sell] - prices[buy]
            }
        }
    }

    var minimum = prices[0]
    var best = 0
    var day = 1
    while (day < prices.size) {
        loopInvariants {
            1 <= day && day <= prices.size
            0 <= minimum && minimum <= 1_000_000_000
            exists<Int> { i -> 0 <= i && i < day && minimum == prices[i] }
            forAll<Int> { i -> (0 <= i && i < day) implies (minimum <= prices[i]) }
            best >= 0
            forAll<Int> { buy ->
                forAll<Int> { sell ->
                    (0 <= buy && buy < sell && sell < day) implies
                        (prices[sell] - prices[buy] <= best)
                }
            }
            best == 0 || exists<Int> { buy ->
                exists<Int> { sell ->
                    0 <= buy && buy < sell && sell < day &&
                        best == prices[sell] - prices[buy]
                }
            }
        }
        val candidate = prices[day] - minimum
        if (candidate > best) best = candidate
        if (prices[day] < minimum) minimum = prices[day]
        day += 1
    }
    return best
}
