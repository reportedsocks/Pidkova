package com.antsyferov.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("name")
    val name: String? = "",
    @SerialName("email")
    val email: String? = "",
    @SerialName("phone")
    val phone: String? = "",
    @SerialName("membership")
    val membership: String? = "",
)