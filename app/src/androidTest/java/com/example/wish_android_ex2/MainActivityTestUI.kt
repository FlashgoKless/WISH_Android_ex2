package com.example.wish_android_ex2

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Rule
import org.junit.Test

class MainActivityUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCorrectInput() {
        ActivityScenario.launch(MainActivity::class.java)

        composeTestRule.onNodeWithTag("numerator_field").performTextInput("1")
        composeTestRule.onNodeWithTag("denominator_field").performTextInput("3")
        composeTestRule.onNodeWithTag("calculate_button").performClick()

        composeTestRule.onNodeWithTag("result_text")
            .assertTextEquals("Decimal period: (3)")

    }

//    @Test
//    fun testZeroDenominator() {
//        // Запускаем Activity
//        ActivityScenario.launch(MainActivity::class.java)
//
//        // Найти поля и ввести значения
//        composeTestRule.onNodeWithTag("numerator_field").performTextInput("1")
//        composeTestRule.onNodeWithTag("denominator_field").performTextInput("0")
//        composeTestRule.onNodeWithTag("calculate_button").performClick()
//
//        // Проверить Toast сообщение
//        onView(withText("Invalid input. Please enter valid numbers."))
//            .inRoot(ToastMatcher())
//            .check(matches(isDisplayed()))
//    }
}
