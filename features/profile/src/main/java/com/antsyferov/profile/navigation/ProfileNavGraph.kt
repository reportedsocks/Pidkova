package com.antsyferov.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.antsyferov.profile.ProfileScreenRoot
import com.antsyferov.ui.analytics.composableWithAnalytics

inline fun <reified T: Any> NavGraphBuilder.profileGraph(
    navController: NavController,
    noinline onNavToAuth: () -> Unit
) {
    navigation<T>(startDestination = AccountInfo) {
        composableWithAnalytics<AccountInfo> {
            ProfileScreenRoot(
                onGoToAuth = onNavToAuth
            )
        }
    }
}