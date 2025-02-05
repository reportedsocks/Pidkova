package com.antsyferov.domain.interfaces

import com.antsyferov.domain.Result
import com.antsyferov.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepo {

    fun getProducts(): Flow<Result<List<Product>>>

    suspend fun getProduct(id: Long): Result<Product>

}