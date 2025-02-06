package com.antsyferov.auth.navigation

import androidx.compose.foundation.layout.Box
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.antsyferov.ui.components.Text

inline fun <reified T: Any> NavGraphBuilder.authGraph(
    navController: NavController
) {
    navigation<T>(startDestination = SignIn) {
        composable<SignIn>{
            Box() {
                Text("Sign in")
            }
        }

        composable<SignUp> {

        }

    }
}