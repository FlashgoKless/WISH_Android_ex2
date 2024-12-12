package com.example.wish_android_ex2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DecimalPeriodCalculatorTest {
    private val calculator = DecimalPeriodCalculator()

    @Test
    fun testFiniteFraction() {
        assertEquals("0", calculator.findDecimalPeriod(4, 2))
        assertEquals("5", calculator.findDecimalPeriod(10, 2))
    }

    @Test
    fun testRepeatingFraction() {
        assertEquals("3(3)", calculator.findDecimalPeriod(10, 3))
        assertEquals("0.(6)", calculator.findDecimalPeriod(2, 3))
    }

    @Test
    fun testNegativeFraction() {
        assertEquals("-3(3)", calculator.findDecimalPeriod(-10, 3))
        assertEquals("-0.(6)", calculator.findDecimalPeriod(2, -3))
    }

    @Test
    fun testZeroNumerator() {
        assertEquals("0", calculator.findDecimalPeriod(0, 5))
    }

    @Test
    fun testDenominatorIsOne() {
        assertEquals("1", calculator.findDecimalPeriod(1, 1))
    }

    @Test
    fun testInvalidInput() {
        val exception = assertThrows<ArithmeticException> {
            calculator.findDecimalPeriod(1, 0)
        }
        assertEquals("/ by zero", exception.message)
    }
}