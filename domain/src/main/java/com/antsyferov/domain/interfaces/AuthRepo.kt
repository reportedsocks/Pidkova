package com.antsyferov.domain.interfaces

import com.antsyferov.domain.Result
import com.antsyferov.domain.models.Session

interface AuthRepo {
    suspend fun signIn(login: String, password: String): Result<Session>
}