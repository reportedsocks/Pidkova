package com.antsyferov.auth.navigation

import com.antsyferov.ui.analytics.AnalyticsDestination
import kotlinx.serialization.Serializable

@Serializable
@AnalyticsDestination(screen = "sign_in")
data object SignIn

@Serializable
@AnalyticsDestination(screen = "sign_up")
data object SignUp