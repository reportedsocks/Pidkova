package com.antsyferov.domain.models

data class Session(
    val accessToken: String,
    val refreshToken: String
)
