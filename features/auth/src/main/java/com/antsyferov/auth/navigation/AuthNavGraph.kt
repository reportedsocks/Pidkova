package com.antsyferov.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.antsyferov.auth.sign_in.SignInScreenRoot

inline fun <reified T: Any> NavGraphBuilder.authGraph(
    navController: NavController,
    noinline onCompletedAuth: () -> Unit,
) {
    navigation<T>(startDestination = SignIn) {
        composable<SignIn>{
            SignInScreenRoot(
                onCompletedAuth = onCompletedAuth
            )
        }

        composable<SignUp> {

        }

    }
}