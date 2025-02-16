package com.antsyferov.products.navigation

import com.antsyferov.ui.analytics.AnalyticsData
import com.antsyferov.ui.analytics.AnalyticsDestination
import kotlinx.serialization.Serializable

@Serializable
@AnalyticsDestination(screen = "products_list")
data object ProductsList

@Serializable
@AnalyticsDestination(screen = "product_details")
data class ProductDetails(
    @property:AnalyticsData
    val productId: Long
)