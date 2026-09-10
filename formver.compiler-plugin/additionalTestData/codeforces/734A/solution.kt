import org.jetbrains.kotlin.formver.plugin.AlwaysVerify
import org.jetbrains.kotlin.formver.plugin.Pure
import org.jetbrains.kotlin.formver.plugin.forAll
import org.jetbrains.kotlin.formver.plugin.implies
import org.jetbrains.kotlin.formver.plugin.loopInvariants
import org.jetbrains.kotlin.formver.plugin.postconditions
import org.jetbrains.kotlin.formver.plugin.preconditions

@Pure
fun antonWinsInPrefix(games: String, end: Int): Int {
    preconditions {
        0 <= end && end <= games.length
    }
    postconditions<Int> { result ->
        result >= 0
        (end == 0) implies (result == 0)
        (end > 0 && games[end - 1] == 'A') implies
            (result == antonWinsInPrefix(games, end - 1) + 1)
        (end > 0 && games[end - 1] == 'D') implies
            (result == antonWinsInPrefix(games, end - 1))
    }

    return if (end == 0) {
        0
    } else if (games[end - 1] == 'A') {
        antonWinsInPrefix(games, end - 1) + 1
    } else {
        antonWinsInPrefix(games, end - 1)
    }
}

@Pure
fun danikWinsInPrefix(games: String, end: Int): Int {
    preconditions {
        0 <= end && end <= games.length
    }
    postconditions<Int> { result ->
        result >= 0
        (end == 0) implies (result == 0)
        (end > 0 && games[end - 1] == 'D') implies
            (result == danikWinsInPrefix(games, end - 1) + 1)
        (end > 0 && games[end - 1] == 'A') implies
            (result == danikWinsInPrefix(games, end - 1))
    }

    return if (end == 0) {
        0
    } else if (games[end - 1] == 'D') {
        danikWinsInPrefix(games, end - 1) + 1
    } else {
        danikWinsInPrefix(games, end - 1)
    }
}

@AlwaysVerify
fun gameWinner(games: String): String {
    preconditions {
        1 <= games.length && games.length <= 100_000
        forAll<Int> { index ->
            (0 <= index && index < games.length) implies
                (games[index] == 'A' || games[index] == 'D')
        }
    }
    postconditions<String> { result ->
        (result == "Anton" || result == "Danik" || result == "Friendship")
        (result == "Anton") ==
            (antonWinsInPrefix(games, games.length) > danikWinsInPrefix(games, games.length))
        (result == "Danik") ==
            (antonWinsInPrefix(games, games.length) < danikWinsInPrefix(games, games.length))
        (result == "Friendship") ==
            (antonWinsInPrefix(games, games.length) == danikWinsInPrefix(games, games.length))
    }

    var index = 0
    var anton = 0
    var danik = 0
    while (index < games.length) {
        loopInvariants {
            0 <= index && index <= games.length
            anton == antonWinsInPrefix(games, index)
            danik == danikWinsInPrefix(games, index)
            anton >= 0 && danik >= 0
            anton + danik == index
        }
        if (games[index] == 'A') {
            anton += 1
        } else {
            danik += 1
        }
        index += 1
    }

    return if (anton > danik) {
        "Anton"
    } else if (anton < danik) {
        "Danik"
    } else {
        "Friendship"
    }
}
