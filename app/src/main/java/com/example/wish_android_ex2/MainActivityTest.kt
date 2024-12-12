package com.example.wish_android_ex2

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.matcher.RootMatchers
import org.hamcrest.Matchers

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testValidInput() {
        onView(withId(R.string.enter_numerator))
            .perform(typeText("10"), closeSoftKeyboard())
        onView(withId(R.string.enter_denominator))
            .perform(typeText("3"), closeSoftKeyboard())
        onView(withId(R.string.calculate)).perform(click())
        onView(withId(R.string.result)).check(matches(withText("3(3)")))
    }

    @Test
    fun testZeroDenominator() {
        onView(withId(R.string.enter_numerator))
            .perform(typeText("10"), closeSoftKeyboard())
        onView(withId(R.string.enter_denominator))
            .perform(typeText("0"), closeSoftKeyboard())
        onView(withId(R.string.calculate)).perform(click())
        onView(withText(R.string.error_message))
            .inRoot(Matchers.is(ToastMatcher()))
            .check(matches(isDisplayed()))
    }
}