package com.antsyferov.network

import com.antsyferov.domain.Result
import com.antsyferov.domain.interfaces.Datastore
import com.antsyferov.network.models.SessionDto
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Qualifier
import org.koin.core.annotation.Single

@Module
@ComponentScan
class NetworkModule {

    @Single
    @Qualifier(Authorized::class)
    fun provideAuthorisedClient(
        datastore: Datastore,
        constantsProvider: ConstantsProvider
    ): HttpClient {
        val client = HttpClient(CIO) {

            install(Logging) {
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

            install(DefaultRequest) {
                url(constantsProvider.getBaseUrl())
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        val accessToken = datastore.getAccessToken().first() ?: ""
                        val refreshToken = datastore.getRefreshToken().first() ?: ""
                        BearerTokens(
                            accessToken = accessToken,
                            refreshToken = refreshToken
                        )
                    }
                    refreshTokens {
                        val result = safeCall<SessionDto> { client.get("refresh") }

                        when(result) {
                            is Result.Success -> {
                                datastore.setAccessToken(result.data.accessToken)
                                datastore.setRefreshToken(result.data.refreshToken)
                                BearerTokens(result.data.accessToken, result.data.refreshToken)
                            }
                            is Result.Error -> {
                                null
                            }
                        }

                    }
                }
            }
        }

        return client
    }

    @Single
    @Qualifier(UnAuthorized::class)
    fun provideUnauthorisedClient(
        constantsProvider: ConstantsProvider
    ): HttpClient {
        val client = HttpClient(CIO) {

            install(Logging) {
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

            install(DefaultRequest) {
                url(constantsProvider.getBaseUrl())
            }
        }

        return client
    }

}