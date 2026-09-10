// FULL_JDK

import org.jetbrains.kotlin.formver.plugin.*

/** Mathematical Fibonacci sequence used as the executable specification. */
@Pure
fun fibonacciSpecification(n: Int): Int {
    preconditions {
        0 <= n && n <= 30
    }
    postconditions<Int> { result ->
        result >= 0 && result <= 832_040
        (n == 0) implies (result == 0)
        (n == 1) implies (result == 1)
        (n > 1) implies
            (result == fibonacciSpecification(n - 1) + fibonacciSpecification(n - 2))
    }

    return if (n == 0) 0
    else if (n == 1) 1
    else fibonacciSpecification(n - 1) + fibonacciSpecification(n - 2)
}

/** Iterative solution of LeetCode 509 over its complete input domain. */
@AlwaysVerify
fun fibonacciNumber(n: Int): Int {
    preconditions {
        0 <= n && n <= 30
    }
    postconditions<Int> { result ->
        result == fibonacciSpecification(n)
        0 <= result && result <= 832_040
        (n == 0) implies (result == 0)
        (n == 1) implies (result == 1)
    }

    if (n == 0) return 0

    var previous = 0
    var current = 1
    var index = 1
    while (index < n) {
        loopInvariants {
            1 <= index && index <= n
            previous == fibonacciSpecification(index - 1)
            current == fibonacciSpecification(index)
            0 <= previous && previous <= 832_040
            0 <= current && current <= 832_040
        }

        // index < n <= 30 makes this at most F(30), so Int addition is safe.
        val next = previous + current
        previous = current
        current = next
        index += 1
    }
    return current
}
