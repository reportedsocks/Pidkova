package com.antsyferov.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

inline fun <reified T: Any> NavGraphBuilder.authGraph(
    navController: NavController
) {
    navigation<T>(startDestination = SignIn) {
        composable<SignIn> {

        }

        composable<SignUp> {

        }

    }
}