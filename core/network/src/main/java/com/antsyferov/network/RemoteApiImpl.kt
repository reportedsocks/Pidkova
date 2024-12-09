package com.antsyferov.network

import com.antsyferov.network.models.Message
import com.antsyferov.network.models.Product
import com.antsyferov.network.models.Profile
import com.antsyferov.network.models.Session
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.sendSerialized
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.get
import io.ktor.websocket.Frame
import io.ktor.websocket.readBytes
import io.ktor.websocket.readReason
import io.ktor.websocket.readText
import io.ktor.websocket.send
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Single
class RemoteApiImpl(
    @Named("authorised") val authorisedClient: HttpClient,
    @Named("unauthorised") val unauthorisedClient: HttpClient,
    private val storage: InMemoryStorage
) {

    private var socket: DefaultClientWebSocketSession? = null

    suspend fun getProducts(): List<Product> {
        return withContext(Dispatchers.IO) {
            val products = authorisedClient.get("products").body<List<Product>>()
            products
        }
    }

    suspend fun getProfile(): Profile {
        return withContext(Dispatchers.IO) {
            val profile = authorisedClient.get("protected/profile").body<Profile>()
            profile
        }
    }


    suspend fun auth() {
        withContext(Dispatchers.IO) {
            val session = unauthorisedClient.get("auth").body<Session>()
            storage.setSession(session)
        }
    }

    suspend fun openSocket() {
        withContext(Dispatchers.IO) {
            socket = authorisedClient.webSocketSession(
                host = "10.0.2.2",
                port = 3000,
                path = "socket"
            )

            launch {
                socket?.let {
                    for (frame in it.incoming) {
                        when(frame) {
                            is Frame.Text -> {
                                frame.readText()
                            }
                            is Frame.Binary -> {
                                frame.readBytes()
                            }
                            is Frame.Close -> {
                                frame.readReason()
                            }
                            else -> {
                                frame.data
                            }
                        }
                    }
                }

            }

        }
    }

    suspend fun sendMessage() {
        withContext(Dispatchers.IO) {
            socket?.sendSerialized(Message())
        }
    }

}