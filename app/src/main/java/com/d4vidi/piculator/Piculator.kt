package com.d4vidi.piculator


class Piculator {
    // Nilakantha series:
    // π = 3 + 4/(2*3*4) - 4/(4*5*6) + 4/(6*7*8) - 4/(8*9*10) + 4/(10*11*12) - 4/(12*13*14) ...
    fun calculate(iterations: Int): Double {
        var result = 3.0
        var sign = 1
        var denomBase = 2L

        for (i in 1..iterations) {
            val fact1 = 1.0 / denomBase
            denomBase++
            val fact2 = 1.0 / denomBase
            denomBase++
            val fact3 = 1.0 / denomBase

            result += sign * (4.0 * fact1 * fact2 * fact3)
            sign *= -1
        }
        return result
    }
}
