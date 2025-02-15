package com.antsyferov.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.antsyferov.main.navigation.Home
import com.antsyferov.main.navigation.MainNavHost
import com.antsyferov.main.navigation.Onboarding
import com.antsyferov.ui.theme.PidkovaTheme
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.androidx.compose.koinViewModel


@Composable
fun PidkovaApp() {
    KoinAndroidContext {
        PidkovaTheme {
            val viewModel = koinViewModel<MainViewModel>()
            val navController = rememberNavController()
            MainNavHost(navController, onOnboardingFinished = viewModel::onOnboardingFinished)

            val hasViewedOnboarding by viewModel.hasViewedOnboarding.collectAsStateWithLifecycle(true)

            LaunchedEffect(hasViewedOnboarding) {
                if (!hasViewedOnboarding) {
                    navController.navigate(
                        route = Onboarding,
                        navOptions = navOptions {
                            popUpTo<Home> {
                                inclusive = true
                            }
                        }
                    )
                }
            }
        }
    }
}