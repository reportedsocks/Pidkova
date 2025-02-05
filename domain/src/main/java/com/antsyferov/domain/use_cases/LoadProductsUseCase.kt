package com.antsyferov.domain.use_cases

import com.antsyferov.domain.Result
import com.antsyferov.domain.interfaces.ProductsRepo
import com.antsyferov.domain.models.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Single

@Single
class LoadProductsUseCase(
    private val productsRepo: ProductsRepo
) {
    operator fun invoke(): Flow<Result<List<Product>>> {
        return productsRepo
            .getProducts()
            .flowOn(Dispatchers.IO)
    }
}