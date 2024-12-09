package com.antsyferov.network.models

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val text: String = "blablablabla"
)
