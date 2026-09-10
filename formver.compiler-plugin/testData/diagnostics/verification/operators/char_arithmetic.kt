// FULL_JDK

import org.jetbrains.kotlin.formver.plugin.*

@AlwaysVerify
fun <!VIPER_TEXT!>charPlusWrapsToCodeRange<!>(c: Char, n: Int): Char {
    preconditions {
        n >= 0
    }
    return c + n
}

@AlwaysVerify
fun <!VIPER_TEXT!>charMinusWrapsToCodeRange<!>(c: Char, n: Int): Char {
    preconditions {
        n >= 0
    }
    return c - n
}

@AlwaysVerify
fun <!VIPER_TEXT!>maxCharPlusOneIsMinChar<!>(): Char {
    postconditions<Char> { result ->
        result == '\u0000'
    }
    return '\uFFFF' + 1
}

@AlwaysVerify
fun <!VIPER_TEXT!>minCharMinusOneIsMaxChar<!>(): Char {
    postconditions<Char> { result ->
        result == '\uFFFF'
    }
    return '\u0000' - 1
}
