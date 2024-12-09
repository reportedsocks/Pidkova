package com.antsyferov.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.serialization.kotlinx.json.json
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
        storage: InMemoryStorage
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
                        val session = storage.getSession()
                        BearerTokens(
                            accessToken = session?.accessToken.toString(),
                            refreshToken = session?.refreshToken.toString()
                        )
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
}