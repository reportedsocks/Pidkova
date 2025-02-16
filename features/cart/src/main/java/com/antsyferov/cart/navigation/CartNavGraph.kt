package com.antsyferov.cart.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.antsyferov.cart.overview.OverviewScreenRoot
import com.antsyferov.ui.analytics.composableWithAnalytics

inline fun <reified T: Any> NavGraphBuilder.cartGraph(
    navController: NavController,
    noinline onNavigateToProductDetails: (Long) -> Unit
) {
    navigation<T>(startDestination = Overview) {
        composableWithAnalytics<Overview> {
            OverviewScreenRoot(
                onNavToProductDetails = onNavigateToProductDetails
            )
        }
    }
}