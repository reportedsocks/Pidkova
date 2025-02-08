package com.antsyferov.network.datasources

import com.antsyferov.domain.Result
import com.antsyferov.domain.map
import com.antsyferov.domain.models.Session
import com.antsyferov.network.UnAuthorized
import com.antsyferov.network.models.SessionDto
import com.antsyferov.network.models.mappers.toDomain
import com.antsyferov.network.safeCall
import com.antsyferov.repository.interfaces.RemoteAuthDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import org.koin.core.annotation.Qualifier
import org.koin.core.annotation.Single

@Single
class KtorAuthDataSource (
    @Qualifier(UnAuthorized::class)
    private val client: HttpClient
): RemoteAuthDataSource {
    override suspend fun signIn(login: String, password: String): Result<Session> {
        //POST, send data in a secure way, etc...
        val result = safeCall<SessionDto> { client.get("auth") }
        return result.map { it.toDomain() }
    }

}