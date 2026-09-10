// FULL_JDK

import org.jetbrains.kotlin.formver.plugin.*

/** Classifies the winner of Codeforces 734A without performing contest I/O. */
@AlwaysVerify
fun antonAndDanik(games: String): String {
    preconditions {
        1 <= games.length && games.length <= 100_000
        forAll<Int> { index ->
            (0 <= index && index < games.length) implies
                (games[index] == 'A' || games[index] == 'D')
        }
    }
    postconditions<String> { result ->
        result == "Anton" || result == "Danik" || result == "Friendship"
    }

    var antonWins = 0
    var danikWins = 0
    var i = 0
    while (i < games.length) {
        loopInvariants {
            0 <= i && i <= games.length
            0 <= antonWins && 0 <= danikWins
            antonWins + danikWins == i
        }

        // The alphabet precondition makes exactly one counter advance.
        if (games[i] == 'A') antonWins += 1 else danikWins += 1
        i += 1
    }

    return if (antonWins > danikWins) "Anton"
    else if (antonWins < danikWins) "Danik"
    else "Friendship"
}
