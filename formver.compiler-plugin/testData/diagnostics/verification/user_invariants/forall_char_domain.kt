// FULL_JDK

import org.jetbrains.kotlin.formver.plugin.*

@AlwaysVerify
fun <!VIPER_TEXT!>everyCharIsNonNegative<!>(): Boolean {
    postconditions<Boolean> {
        forAll<Char> { it >= '\u0000' }
    }
    return true
}

@AlwaysVerify
fun <!VIPER_TEXT!>everyCharIsAtMostMax<!>(): Boolean {
    postconditions<Boolean> {
        forAll<Char> { it <= '\uFFFF' }
    }
    return true
}
