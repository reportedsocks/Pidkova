package com.antsyferov.network

import com.antsyferov.network.models.Product
import com.antsyferov.network.models.Profile
import com.antsyferov.network.models.Session
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Single
class RemoteApiImpl(
    @Named("authorised") val authorisedClient: HttpClient,
    @Named("unauthorised") val unauthorisedClient: HttpClient,
    private val storage: InMemoryStorage
) {

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

}