package com.antsyferov.repository.repos

import com.antsyferov.domain.Result
import com.antsyferov.domain.interfaces.AuthRepo
import com.antsyferov.domain.models.Session
import com.antsyferov.repository.interfaces.RemoteAuthDataSource

class AuthRepoImpl(
    private val authDataSource: RemoteAuthDataSource
): AuthRepo {
    override suspend fun signIn(login: String, password: String): Result<Session> {
        return authDataSource.signIn(login, password)
    }
}