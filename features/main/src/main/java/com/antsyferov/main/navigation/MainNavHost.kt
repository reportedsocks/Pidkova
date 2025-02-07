package com.antsyferov.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.antsyferov.auth.navigation.authGraph
import com.antsyferov.home.navigation.homeGraph
import com.antsyferov.onboarding.navigation.onboardingGraph


@Composable
fun MainNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Home
    ) {

        homeGraph<Home>(
            navController = navController,
            onNavToAuth = { navController.navigate(
                route = Auth,
                navOptions = navOptions {
                    popUpTo<Home> {
                        inclusive = true
                    }
                })
            }
        )

        authGraph<Auth>(navController)

        onboardingGraph<Onboarding>(navController)
    }
}