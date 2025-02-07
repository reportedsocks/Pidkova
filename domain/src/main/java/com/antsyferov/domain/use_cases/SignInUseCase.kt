package com.antsyferov.domain.use_cases

import com.antsyferov.domain.Result
import com.antsyferov.domain.interfaces.AuthRepo
import com.antsyferov.domain.interfaces.Datastore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single

@Single
class SignInUseCase(
    private val authRepo: AuthRepo,
    private val datastore: Datastore
) {
    suspend operator fun invoke(login: String, password: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            val result = authRepo.signIn(login, password)
            when(result) {
                is Result.Success -> {
                    datastore.setAccessToken(result.data.accessToken)
                    datastore.setRefreshToken(result.data.refreshToken)
                    Result.Success(Unit)
                }
                is Result.Error -> {
                    Result.Error(result.error)
                }
            }
        }
    }
}