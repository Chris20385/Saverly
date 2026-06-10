package com.example.saverly

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class SaverlyUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreenShowsTitle() {

        composeTestRule.setContent {
            HomeScreen(
                goAdd = {},
                goHistory = {},
                goSettings = {}
            )
        }

        composeTestRule
            .onNodeWithText("Todays Spending")
            .assertExists()
    }
}
