package com.example.wish_android_ex2

class DecimalPeriodCalculator {
    fun findDecimalPeriod(numerator: Int, denominator: Int): String {
        val isNegative = (numerator < 0) xor (denominator < 0)

        val absNumerator = kotlin.math.abs(numerator)
        val absDenominator = kotlin.math.abs(denominator)

        val remainders = mutableMapOf<Int, Int>()
        var remainder = absNumerator % absDenominator
        val decimals = StringBuilder()
        var position = 0

        while (remainder != 0 && !remainders.contains(remainder)) {
            remainders[remainder] = position
            remainder *= 10
            decimals.append(remainder / absDenominator)
            remainder %= absDenominator
            position++
        }

        return if (remainder == 0) {
            "0" // Дробь конечна
        } else {
            val start = remainders[remainder] ?: 0
            val nonRepeating = decimals.substring(0, start)
            val repeating = decimals.substring(start)
            if (isNegative) {
                "-$nonRepeating($repeating)"
            } else {
                "$nonRepeating($repeating)"
            }
        }
    }
}