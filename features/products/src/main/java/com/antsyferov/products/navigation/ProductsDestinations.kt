package com.antsyferov.products.navigation

import kotlinx.serialization.Serializable

@Serializable
data object ProductsList

@Serializable
data class ProductDetails(val productId: Long)