package com.antsyferov.pidkova

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import com.antsyferov.main.PidkovaApp
import com.antsyferov.products.models.ProductUi
import com.antsyferov.products.product_details.ProductDetailsScreen
import com.antsyferov.products.product_details.redux.ProductDetailsState
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class AppTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun appShowOnboardingAndThenLoadsProducts() = runTest {
        composeTestRule.setContent {
            PidkovaApp()
        }

        composeTestRule.waitForIdle()
        composeTestRule.onAllNodes(hasScrollAction()).onFirst().performTouchInput { swipeLeft() }
        composeTestRule.waitForIdle()
        composeTestRule.onAllNodes(hasScrollAction()).onFirst().performTouchInput { swipeLeft() }
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Go to the app").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithContentDescription("Home tab").assertExists()
        composeTestRule.waitUntilAtLeastOneExists(hasTestTag("product_item"))
    }

}