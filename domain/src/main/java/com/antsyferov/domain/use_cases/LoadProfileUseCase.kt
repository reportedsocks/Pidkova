package com.antsyferov.domain.use_cases

import com.antsyferov.domain.Result
import com.antsyferov.domain.interfaces.ProfileRepo
import com.antsyferov.domain.models.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single

@Single
class LoadProfileUseCase(
    private val profileRepo: ProfileRepo
) {
    suspend operator fun invoke(): Result<Profile> {
        return withContext(Dispatchers.IO){
            profileRepo.getProfile()
        }
    }
}