package com.antsyferov.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

inline fun <reified T: Any> NavGraphBuilder.onboardingGraph(
    navController: NavController
) {
    navigation<T>(startDestination = Instructions) {
        composable<Instructions> {

        }
    }
}