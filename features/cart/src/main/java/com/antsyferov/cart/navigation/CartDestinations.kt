package com.antsyferov.cart.navigation

import com.antsyferov.ui.analytics.AnalyticsDestination
import kotlinx.serialization.Serializable

@Serializable
@AnalyticsDestination(screen = "overview")
data object Overview