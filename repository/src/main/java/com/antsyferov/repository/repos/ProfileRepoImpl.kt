package com.antsyferov.repository.repos

import com.antsyferov.domain.Result
import com.antsyferov.domain.interfaces.ProfileRepo
import com.antsyferov.domain.models.Profile
import com.antsyferov.repository.interfaces.RemoteProfileDataSource

class ProfileRepoImpl(
    private val profileDataSource: RemoteProfileDataSource
): ProfileRepo {
    override suspend fun getProfile(): Result<Profile> {
        return profileDataSource.getProfile()
    }
}