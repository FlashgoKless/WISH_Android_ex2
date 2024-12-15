package com.example.wish_android_ex2

import org.junit.Assert.assertEquals
import org.junit.Test

class DecimalPeriodCalculatorTest {

    private val calculator = DecimalPeriodCalculator()

    @Test
    fun testFiniteDecimal() {
        val result = calculator.findDecimalPeriod(1, 2)
        assertEquals("0", result)
    }

    @Test
    fun testRepeatingDecimal() {
        val result = calculator.findDecimalPeriod(1, 3)
        assertEquals("(3)", result)
    }

    @Test
    fun testNonRepeatingWithRepeatingPart() {
        val result = calculator.findDecimalPeriod(1, 6)
        assertEquals("1(6)", result)
    }

    @Test
    fun testZeroDenominator() {
        try {
            calculator.findDecimalPeriod(1, 0)
        } catch (e: ArithmeticException) {
            assertEquals("/ by zero", e.message)
        }
    }

    @Test
    fun testZeroNumerator() {
        val result = calculator.findDecimalPeriod(0, 5)
        assertEquals("0", result)
    }
}
