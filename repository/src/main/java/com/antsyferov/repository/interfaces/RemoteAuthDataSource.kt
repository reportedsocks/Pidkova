package com.antsyferov.repository.interfaces

import com.antsyferov.domain.Result
import com.antsyferov.domain.models.Session

interface RemoteAuthDataSource {
    suspend fun signIn(login: String, password: String): Result<Session>
}