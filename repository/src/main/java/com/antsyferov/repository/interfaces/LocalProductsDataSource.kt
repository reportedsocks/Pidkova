package com.antsyferov.repository.interfaces

import com.antsyferov.domain.Result
import com.antsyferov.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface LocalProductsDataSource {

    suspend fun updateProducts(products: List<Product>): Result<Unit>

    fun getProducts(): Flow<List<Product>>

    suspend fun getProduct(id: Long): Result<Product>

}
