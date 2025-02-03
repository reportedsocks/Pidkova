package com.antsyferov.repository.interfaces

import com.antsyferov.domain.Result
import com.antsyferov.domain.models.Product

interface RemoteProductsDataSource {
    suspend fun getProducts(): Result<List<Product>>
}