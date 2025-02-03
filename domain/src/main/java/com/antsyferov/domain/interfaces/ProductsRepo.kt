package com.antsyferov.domain.interfaces

import com.antsyferov.domain.Result
import com.antsyferov.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepo {

    suspend fun getProducts(): Flow<Result<List<Product>>>

}