package com.antsyferov.network.models.mappers

import com.antsyferov.domain.models.Session
import com.antsyferov.network.models.SessionDto

fun SessionDto.toDomain(): Session {
    return Session(
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}