package com.antsyferov.domain.interfaces

import com.antsyferov.domain.Result
import com.antsyferov.domain.models.Profile

interface ProfileRepo {
    suspend fun getProfile(): Result<Profile>
}