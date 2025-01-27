package com.antsyferov.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.antsyferov.main.navigation.MainNavHost
import com.antsyferov.ui.theme.PidkovaTheme
import org.koin.androidx.compose.KoinAndroidContext


@Composable
fun PidkovaApp() {
    //TODO get a VM
    // check for auth
    // check for onboarding complete
    KoinAndroidContext {
        PidkovaTheme {
            val navController = rememberNavController()
            MainNavHost(navController)
        }
    }
}