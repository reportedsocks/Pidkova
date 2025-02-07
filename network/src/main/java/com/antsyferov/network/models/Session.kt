package com.antsyferov.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SessionDto(
    @SerialName("access_token")
    val accessToken: String ="",
    @SerialName("refresh_token")
    val refreshToken: String = "",
)
