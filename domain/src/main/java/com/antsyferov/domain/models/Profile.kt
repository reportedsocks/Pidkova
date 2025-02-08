package com.antsyferov.domain.models

data class Profile(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val membership: String
)
