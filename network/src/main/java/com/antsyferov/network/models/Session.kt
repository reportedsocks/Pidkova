package com.antsyferov.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Session(
    @SerialName("access_token")
    val accessToken: Int = 0,
    @SerialName("refresh_token")
    val refreshToken: Int = 0,
)
