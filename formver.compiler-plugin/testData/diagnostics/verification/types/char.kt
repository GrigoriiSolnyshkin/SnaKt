// FULL_JDK

import org.jetbrains.kotlin.formver.plugin.*

class CharBox(val char: Char)

@AlwaysVerify
fun <!VIPER_TEXT!>charLiteralIsInCodeRange<!>(): Char = 'a'

@AlwaysVerify
fun <!VIPER_TEXT!>requiresNonNegativeChar<!>(c: Char): Char {
    preconditions {
        c >= '\u0000'
    }
    return c
}

@AlwaysVerify
fun <!VIPER_TEXT!>passesCharParameterOn<!>(c: Char): Char = requiresNonNegativeChar(c)

@AlwaysVerify
fun <!VIPER_TEXT!>nullableCharRoundTrip<!>(c: Char?): Char? = c

@AlwaysVerify
fun <!VIPER_TEXT!>charPropertyIsInCodeRange<!>(box: CharBox): Char =
    requiresNonNegativeChar(box.char)

@AlwaysVerify
fun <!VIPER_TEXT!>stringElementIsInCodeRange<!>(s: String): Char {
    preconditions {
        s.length > 0
    }
    return requiresNonNegativeChar(s[0])
}

@AlwaysVerify
fun <!VIPER_TEXT!>advanceCharInLoop<!>(n: Int): Char {
    preconditions {
        n >= 0
    }
    var c = 'a'
    var i = 0
    while (i < n) {
        loopInvariants {
            i <= n
        }
        c += 1
        i += 1
    }
    return requiresNonNegativeChar(c)
}
