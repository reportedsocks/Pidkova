package com.antsyferov.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.antsyferov.onboarding.composables.OnboardingScreen

inline fun <reified T: Any> NavGraphBuilder.onboardingGraph(
    noinline onOnboardingFinished: () -> Unit
) {
    navigation<T>(startDestination = Instructions) {
        composable<Instructions> {
            OnboardingScreen(
                onOnboardingFinished = onOnboardingFinished
            )
        }
    }
}