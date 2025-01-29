package com.antsyferov.products.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.antsyferov.products.products_list.ProductListRoot

inline fun <reified T: Any> NavGraphBuilder.productsGraph(
    navController: NavController
) {
    navigation<T>(startDestination = ProductsList) {
        composable<ProductsList> {
            ProductListRoot(
                onNavToProductDetails = { }
            )
        }
    }
}