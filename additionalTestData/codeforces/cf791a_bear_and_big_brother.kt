package additionalTestData.codeforces

import org.jetbrains.kotlin.formver.plugin.AlwaysVerify
import org.jetbrains.kotlin.formver.plugin.loopInvariants
import org.jetbrains.kotlin.formver.plugin.postconditions
import org.jetbrains.kotlin.formver.plugin.preconditions

/**
 * Codeforces 791A: https://codeforces.com/problemset/problem/791/A
 *
 * Contract: 1 <= [limak] <= [bob] <= 10. Return the least positive year y for
 * which multiplying Limak's weight by 3 and Bob's by 2 exactly y times leaves
 * Limak strictly heavier.
 */
@AlwaysVerify
fun yearsUntilLarger(limak: Int, bob: Int): Int {
    preconditions { 1 <= limak && limak <= bob && bob <= 10 }
    postconditions<Int> { years ->
        (years == 1 && 3 * limak > 2 * bob) ||
            (years == 2 && 3 * limak <= 2 * bob && 9 * limak > 4 * bob) ||
            (years == 3 && 9 * limak <= 4 * bob && 27 * limak > 8 * bob) ||
            (years == 4 && 27 * limak <= 8 * bob && 81 * limak > 16 * bob) ||
            (years == 5 && 81 * limak <= 16 * bob && 243 * limak > 32 * bob) ||
            (years == 6 && 243 * limak <= 32 * bob && 729 * limak > 64 * bob)
    }

    var limakWeight = limak
    var bobWeight = bob
    var years = 0
    while (limakWeight <= bobWeight) {
        loopInvariants {
            0 <= years && years <= 6
            1 <= limakWeight && limakWeight <= 7_290
            1 <= bobWeight && bobWeight <= 640
            (years == 0 && limakWeight == limak && bobWeight == bob) ||
                (years == 1 && limakWeight == 3 * limak && bobWeight == 2 * bob) ||
                (years == 2 && limakWeight == 9 * limak && bobWeight == 4 * bob) ||
                (years == 3 && limakWeight == 27 * limak && bobWeight == 8 * bob) ||
                (years == 4 && limakWeight == 81 * limak && bobWeight == 16 * bob) ||
                (years == 5 && limakWeight == 243 * limak && bobWeight == 32 * bob) ||
                (years == 6 && limakWeight == 729 * limak && bobWeight == 64 * bob)
        }
        limakWeight *= 3
        bobWeight *= 2
        years += 1
    }
    return years
}
