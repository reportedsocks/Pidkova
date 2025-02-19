package com.antsyferov.products

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.antsyferov.products.models.ProductUi
import com.antsyferov.products.product_details.ProductDetailsScreen
import com.antsyferov.products.product_details.redux.ProductDetailsState
import org.junit.Rule
import org.junit.Test

class ProductDetailsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun productDetailsAreShownWhenLoaded() {
        composeTestRule.setContent {
            ProductDetailsScreen(
                state = ProductDetailsState(
                    product = ProductUi(
                        id = 1,
                        name = "product",
                        brand = "brand",
                        price = 10f,
                        rating = 4f,
                        description = "description"
                    ),
                    isLoading = false
                ),
                onEvent = {}
            )
        }

        composeTestRule.onNodeWithText("product").assertExists()
        composeTestRule.onNodeWithText("brand").assertExists()
        composeTestRule.onNodeWithText("description").assertExists()
    }

    @Test
    fun productDetailsAreHiddenWhileLoading() {
        composeTestRule.setContent {
            ProductDetailsScreen(
                state = ProductDetailsState(
                    product = ProductUi(
                        id = 1,
                        name = "product",
                        brand = "brand",
                        price = 10f,
                        rating = 4f,
                        description = "description"
                    ),
                    isLoading = true
                ),
                onEvent = {}
            )
        }

        composeTestRule.onNodeWithText("product").assertDoesNotExist()
        composeTestRule.onNodeWithText("brand").assertDoesNotExist()
        composeTestRule.onNodeWithText("description").assertDoesNotExist()
    }

}