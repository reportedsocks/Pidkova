package com.antsyferov.network.datasources

import com.antsyferov.domain.PidkovaException
import com.antsyferov.domain.Result
import com.antsyferov.domain.map
import com.antsyferov.domain.models.Product
import com.antsyferov.network.models.ProductDto
import com.antsyferov.network.models.mappers.toDomain
import com.antsyferov.network.safeCall
import com.antsyferov.repository.interfaces.RemoteProductsDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.delay

class KtorProductsDataSource(
    private val client: HttpClient
): RemoteProductsDataSource {
    override suspend fun getProducts(): Result<List<Product>> {
        val simulateError = false
        if (simulateError) {
            delay(2000)
            return Result.Error(PidkovaException.SERVER)
        } else {
            val productsDto = safeCall<List<ProductDto>> { client.get("products") }
            return productsDto.map { it.map { productDto -> productDto.toDomain() } }
        }
    }
}