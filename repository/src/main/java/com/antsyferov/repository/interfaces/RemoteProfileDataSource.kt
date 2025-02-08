package com.antsyferov.repository.interfaces

import com.antsyferov.domain.Result
import com.antsyferov.domain.models.Profile

interface RemoteProfileDataSource {
    suspend fun getProfile(): Result<Profile>
}