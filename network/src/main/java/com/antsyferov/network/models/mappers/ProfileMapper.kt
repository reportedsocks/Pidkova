package com.antsyferov.network.models.mappers

import com.antsyferov.domain.models.Profile
import com.antsyferov.network.models.ProfileDto

fun ProfileDto.toDomain(): Profile {
    return Profile(
        id = id?.toInt() ?: 0,
        name = name ?: "",
        email = email ?: "",
        phone = phone ?: "",
        membership = membership ?: ""
    )
}