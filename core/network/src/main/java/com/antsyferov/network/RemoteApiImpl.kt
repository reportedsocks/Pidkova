package com.antsyferov.network

import com.antsyferov.network.models.Product
import com.antsyferov.network.models.Profile
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Provided
import org.koin.core.annotation.Single

@Single
class RemoteApiImpl(@Provided val client: HttpClient) {

    suspend fun getProducts(): List<Product> {
        return withContext(Dispatchers.IO) {
            val products = client.get("products").body<List<Product>>()
            products
        }
    }

    suspend fun getProfile(): Profile {
        return withContext(Dispatchers.IO) {
            val profile = client.get("protected/profile").body<Profile>()
            profile
        }
    }

}