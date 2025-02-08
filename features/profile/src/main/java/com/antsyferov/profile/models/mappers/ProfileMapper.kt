package com.antsyferov.profile.models.mappers

import com.antsyferov.domain.models.Profile
import com.antsyferov.profile.models.ProfileUi

fun Profile.toUi(): ProfileUi {
    return ProfileUi(
        id = id,
        name = name,
        email = email,
        phone = phone,
        membership = membership
    )
}