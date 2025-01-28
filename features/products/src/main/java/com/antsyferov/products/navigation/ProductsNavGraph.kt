package com.antsyferov.products.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.antsyferov.ui.components.Text

inline fun <reified T: Any> NavGraphBuilder.productsGraph(
    navController: NavController
) {
    navigation<T>(startDestination = ProductsList) {
        composable<ProductsList> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Products")
            }
        }
    }
}