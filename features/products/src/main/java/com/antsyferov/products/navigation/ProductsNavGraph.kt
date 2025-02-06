package com.antsyferov.products.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.antsyferov.products.product_details.ProductDetailsRoot
import com.antsyferov.products.products_list.ProductListRoot

inline fun <reified T: Any> NavGraphBuilder.productsGraph(
    navController: NavController,
    noinline onNavToCart: () -> Unit
) {
    navigation<T>(startDestination = ProductsList) {
        composable<ProductsList> {
            ProductListRoot(
                onNavToProductDetails = { navController.navigate(ProductDetails(it)) }
            )
        }

        composable<ProductDetails>(
            deepLinks = listOf(navDeepLink<ProductDetails>(basePath = "app://pidkova/details/"))
        ) { navBackStackEntry ->
            val productId = navBackStackEntry.toRoute<ProductDetails>().productId
            ProductDetailsRoot(
                productId = productId,
                onNavBack = { navController.navigateUp() },
                onNavToCart = onNavToCart
            )
        }
    }
}