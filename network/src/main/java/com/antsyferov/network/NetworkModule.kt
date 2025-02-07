package com.antsyferov.network

import com.antsyferov.domain.interfaces.Datastore
import com.antsyferov.network.datasources.KtorProductsDataSource
import com.antsyferov.repository.interfaces.RemoteProductsDataSource
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.first
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.protobuf.ProtoBuf
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Module
@ComponentScan
class NetworkModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Single
    @Named("authorised")
    fun provideAuthorisedClient(
        datastore: Datastore
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
                url("http://10.0.2.2:3000/")
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
                        //TODO
                        //client.get("refresh")
                        BearerTokens(oldTokens?.accessToken ?: "", oldTokens?.refreshToken)
                    }
                }
            }

            install(WebSockets) {
                pingIntervalMillis = 10000

                contentConverter = KotlinxWebsocketSerializationConverter(ProtoBuf)
            }
        }

        return client
    }

    @Single
    @Named("unauthorised")
    fun provideUnauthorisedClient(): HttpClient {
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
                url("http://10.0.2.2:3000/")
            }
        }

        return client
    }

    @Single
    fun provideRemoteProductsDataSource(
        @Named("unauthorised") client: HttpClient
    ): RemoteProductsDataSource {
        return KtorProductsDataSource(client)
    }
}