package com.example.wish_android_ex2

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Test

class MainActivityUITest {

    @Test
    fun testCorrectInput() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.string.enter_numerator))
            .perform(typeText("1"), closeSoftKeyboard())

        onView(withId(R.string.enter_denominator))
            .perform(typeText("3"), closeSoftKeyboard())

        onView(withId(R.string.calculate)).perform(click())

        onView(withId(R.string.result))
            .check(matches(withText("Результат: (3)")))
    }

    @Test
    fun testZeroDenominator() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.string.enter_numerator))
            .perform(typeText("1"), closeSoftKeyboard())

        onView(withId(R.string.enter_denominator))
            .perform(typeText("0"), closeSoftKeyboard())

        onView(withId(R.string.calculate)).perform(click())

        onView(withText(R.string.error_message))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }
}
