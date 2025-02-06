package com.antsyferov.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.antsyferov.products.navigation.ProductDetails

const val DEEPLINK_BASE_PATH = "app://pidkova/"
const val PRODUCTS_DESTINATION = "products"
const val CART_DESTINATION = "cart"

@Composable
fun DeepLinkHandler(
    navController: NavController,
    destination: String?,
    productId: Long?
) {
    LaunchedEffect(destination, productId) {
        when(destination) {
            PRODUCTS_DESTINATION -> {
                if (productId != null) {
                    navController.navigate(
                        route = ProductDetails(productId),
                        navOptions = navOptions {
                            changeTabNavOptions(navController = navController)
                        }
                    )
                }
            }
            CART_DESTINATION -> {
                navController.navigate(
                    route = Cart,
                    navOptions = navOptions {
                        changeTabNavOptions(navController = navController)
                    }
                )
            }
        }
    }
}