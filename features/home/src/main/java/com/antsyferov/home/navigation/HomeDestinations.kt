package com.antsyferov.home.navigation

import kotlinx.serialization.Serializable

@Serializable
data class HomeTabs(
    val destination: String? = null,
    val productId: Long? = null
)

interface Tab

@Serializable
data object Products: Tab

@Serializable
data object Cart: Tab

@Serializable
data object Profile: Tab


