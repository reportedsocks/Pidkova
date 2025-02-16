package com.antsyferov.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.antsyferov.auth.sign_in.SignInScreenRoot
import com.antsyferov.ui.analytics.composableWithAnalytics

inline fun <reified T: Any> NavGraphBuilder.authGraph(
    navController: NavController,
    noinline onCompletedAuth: () -> Unit,
) {
    navigation<T>(startDestination = SignIn) {
        composableWithAnalytics<SignIn>{
            SignInScreenRoot(
                onCompletedAuth = onCompletedAuth
            )
        }

        composableWithAnalytics<SignUp> {

        }

    }
}