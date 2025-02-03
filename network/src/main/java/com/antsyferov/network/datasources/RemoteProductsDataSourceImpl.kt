package com.antsyferov.network.datasources

import com.antsyferov.domain.Result
import com.antsyferov.domain.map
import com.antsyferov.domain.models.Product
import com.antsyferov.network.models.ProductDto
import com.antsyferov.network.models.mappers.toDomain
import com.antsyferov.network.safeCall
import com.antsyferov.repository.interfaces.RemoteProductsDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteProductsDataSourceImpl(
    private val client: HttpClient
): RemoteProductsDataSource {
    override suspend fun getProducts(): Result<List<Product>> {
        val productsDto = safeCall<List<ProductDto>> { client.get("products") }
        return productsDto.map { it.map { productDto -> productDto.toDomain() } }
    }
}