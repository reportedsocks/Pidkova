package com.antsyferov.network.datasources

import com.antsyferov.domain.Result
import com.antsyferov.domain.map
import com.antsyferov.domain.models.Profile
import com.antsyferov.network.Authorized
import com.antsyferov.network.models.ProfileDto
import com.antsyferov.network.models.mappers.toDomain
import com.antsyferov.network.safeCall
import com.antsyferov.repository.interfaces.RemoteProfileDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import org.koin.core.annotation.Qualifier
import org.koin.core.annotation.Single

@Single
class KtorProfileDataSource(
    @Qualifier(Authorized::class)
    private val client: HttpClient
): RemoteProfileDataSource {
    override suspend fun getProfile(): Result<Profile> {
        val result = safeCall<ProfileDto> { client.get("protected/profile") }
        return result.map { it.toDomain() }
    }
}