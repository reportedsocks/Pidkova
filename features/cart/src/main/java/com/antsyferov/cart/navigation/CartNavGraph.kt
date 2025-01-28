package com.antsyferov.cart.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.antsyferov.ui.components.Text

inline fun <reified T: Any> NavGraphBuilder.cartGraph(
    navController: NavController
) {
    navigation<T>(startDestination = Overview) {
        composable<Overview> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Cart")
            }
        }
    }
}