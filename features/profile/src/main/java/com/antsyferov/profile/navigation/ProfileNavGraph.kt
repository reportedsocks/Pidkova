package com.antsyferov.profile.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.antsyferov.ui.components.Button
import com.antsyferov.ui.components.Text

inline fun <reified T: Any> NavGraphBuilder.profileGraph(
    navController: NavController,
    noinline onNavToAuth: () -> Unit
) {
    navigation<T>(startDestination = AccountInfo) {
        composable<AccountInfo> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                //Text("Profile")
                Button(
                    text = "auth",
                    onClick = onNavToAuth
                )
            }
        }
    }
}