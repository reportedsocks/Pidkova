package com.antsyferov.profile.navigation

import com.antsyferov.ui.analytics.AnalyticsDestination
import kotlinx.serialization.Serializable

@Serializable
@AnalyticsDestination(screen = "account_info")
data object AccountInfo