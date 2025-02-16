package com.antsyferov.onboarding.navigation

import com.antsyferov.ui.analytics.AnalyticsDestination
import kotlinx.serialization.Serializable

@Serializable
@AnalyticsDestination(screen = "instructions")
data object Instructions